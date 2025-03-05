package com.strong.sercurity.service;

import com.strong.common.entity.system.User;

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
    void validate(User user);
}
