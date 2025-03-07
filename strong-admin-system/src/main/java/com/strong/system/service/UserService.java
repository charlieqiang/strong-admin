package com.strong.system.service;


import com.strong.system.entity.User;

/**
 * @author charlie
 * @date 2025/2/13 11:21
 **/
public interface UserService {
    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 通过account查询用户
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);
}
