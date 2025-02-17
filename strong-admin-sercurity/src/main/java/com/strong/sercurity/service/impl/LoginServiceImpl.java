package com.strong.sercurity.service.impl;

import com.strong.common.cache.RedisCache;
import com.strong.common.constant.CacheConstants;
import com.strong.common.enums.SystemCodeEnum;
import com.strong.common.exception.StrongException;
import com.strong.sercurity.entity.LoginUser;
import com.strong.sercurity.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public LoginUser login(String account, String password, String captchaCode, String captchaId) {
        // 验证码校验
        validateCaptcha(captchaCode, captchaId);
        // 用户验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account, password);
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        return loginUser;
    }

    private void validateCaptcha(String captchaCode, String captchaId) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + (captchaId == null ? "" : captchaId);
        String captcha = redisCache.getCacheObject(verifyKey);
        if (captcha == null) {
            throw new StrongException(SystemCodeEnum.OPERATE_ERROR, "验证码已过期，请重新获取");
        }
        redisCache.deleteObject(verifyKey);
        if (!captchaCode.equalsIgnoreCase(captcha)) {
            throw new StrongException(SystemCodeEnum.OPERATE_ERROR, "验证码错误");
        }
    }
}
