package com.strong.sercurity.service;

import com.strong.sercurity.entity.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author charlie
 * @date 2025/2/18 13:09
 **/
public interface TokenService {

    /**
     * 通过loginUser生成token
     *
     * @param loginUser
     * @return
     */
    String createToken(LoginUser loginUser);

    /**
     * 通过请求获取LoginUser
     *
     * @param request
     * @return
     */
    LoginUser getLoginUser(HttpServletRequest request);

    /**
     * 验证token
     *
     * @param loginUser
     */
    void verifyToken(LoginUser loginUser);
}
