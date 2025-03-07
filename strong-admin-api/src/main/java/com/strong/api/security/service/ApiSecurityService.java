package com.strong.api.security.service;

import com.strong.api.security.dto.LoginUserDto;

/**
 * @author charlie
 * @date 2025/3/6 16:55
 **/
public interface ApiSecurityService {
    /**
     * 获取登录用户（通过security上下文）
     *
     * @return
     */
    LoginUserDto getLoginUser();
}
