package com.strong.security.service.impl;

import com.strong.api.system.dto.UserDto;
import com.strong.api.system.service.ApiUserService;
import com.strong.common.exception.CustomizeException;
import com.strong.security.entity.LoginUser;
import com.strong.security.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ApiUserService apiUserService;

    @Autowired
    private PasswordService passwordService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserDto user = apiUserService.getUserByAccount(account);
        if (null == user) {
            log.info("登录用户：{} 不存在.", account);
            throw new CustomizeException();
        }

        passwordService.validate(user);
        return new LoginUser(user);
    }
}
