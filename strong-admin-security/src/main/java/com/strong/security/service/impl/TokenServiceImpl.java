package com.strong.security.service.impl;

import com.strong.common.cache.RedisCache;
import com.strong.common.constant.CacheConstants;
import com.strong.common.constant.Constants;
import com.strong.common.exception.CustomizeException;
import com.strong.common.util.snowflakeid.SnowflakeIdWorker;
import com.strong.security.entity.LoginUser;
import com.strong.security.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author charlie
 * @date 2025/2/18 13:10
 **/
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private int expireTime;

    @Autowired
    private RedisCache redisCache;

    public final static String AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    private static final long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Override
    public String createToken(LoginUser loginUser) {
        String tokenId = String.valueOf(SnowflakeIdWorker.getInstance().nextId());
        loginUser.setTokenId(tokenId);
        refreshToken(loginUser);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_TOKEN_ID, tokenId);
        return createToken(claims);
    }

    public void refreshToken(LoginUser loginUser) {
        long currentTimeMillis = System.currentTimeMillis();
        loginUser.setLoginTime(currentTimeMillis);
        loginUser.setExpireTime(currentTimeMillis + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getTokenId());
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String uuid) {
        return CacheConstants.LOGIN_TOKEN_KEY + uuid;
    }

    @Override
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            Claims claims = parseToken(token);
            // 解析对应的权限以及用户信息
            String tokenId = (String) claims.get(Constants.LOGIN_USER_TOKEN_ID);
            String userKey = CacheConstants.LOGIN_TOKEN_KEY + tokenId;
            return redisCache.getCacheObject(userKey);
        } catch (Exception e) {
            throw new CustomizeException("获取用户信息异常");
        }
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
        }
        return token;
    }

    @Override
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
