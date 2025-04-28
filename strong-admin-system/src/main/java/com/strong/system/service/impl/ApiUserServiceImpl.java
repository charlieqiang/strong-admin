package com.strong.system.service.impl;

import com.strong.api.system.dto.UserDto;
import com.strong.api.system.service.ApiUserService;
import com.strong.system.build.UserToDtoBuilder;
import com.strong.system.entity.User;
import com.strong.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author charlie
 * @date 2025/3/6 16:38
 **/
@Service
public class ApiUserServiceImpl implements ApiUserService {

    @Autowired
    private UserService userService;

    @Override
    public UserDto getUserByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return null;
        }
        User user = userService.getUserByAccount(account);
        return UserToDtoBuilder.build(user);
    }
}
