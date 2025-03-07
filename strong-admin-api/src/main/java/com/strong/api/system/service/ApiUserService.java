package com.strong.api.system.service;

import com.strong.api.system.dto.UserDto;

/**
 * @author charlie
 * @date 2025/3/6 16:26
 **/
public interface ApiUserService {
    /**
     * 通过账号获取用户
     *
     * @param account
     * @return
     */
    UserDto getUserByAccount(String account);
}
