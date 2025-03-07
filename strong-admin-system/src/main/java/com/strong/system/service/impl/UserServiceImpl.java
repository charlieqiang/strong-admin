package com.strong.system.service.impl;

import com.strong.system.entity.User;
import com.strong.system.mapper.UserMapper;
import com.strong.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author charlie
 * @date 2025/2/13 11:22
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return null;
        }
        return userMapper.getUserByAccount(account);
    }
}
