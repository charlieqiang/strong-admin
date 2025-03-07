package com.strong.security.service;

import com.strong.api.system.dto.UserDto;

/**
 * @author charlie
 * @date 2025/2/19 13:26
 **/
public interface PasswordService {
    /**
     * 检验用户
     *
     * @param user
     */
    void validate(UserDto user);
}
