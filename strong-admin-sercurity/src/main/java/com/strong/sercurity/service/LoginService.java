package com.strong.sercurity.service;

import com.strong.common.entity.security.LoginUser;

/**
 * @author charlie
 * @date 2025/2/13 16:04
 **/
public interface LoginService {

    /**
     * 登录验证
     *
     * @param account 用户名
     * @param password 密码
     * @param captchaCode 验证码
     * @param captchaId 唯一标识
     * @return 结果
     */
    LoginUser login(String account, String password, String captchaCode, String captchaId);
}
