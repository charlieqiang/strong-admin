package com.strong.sercurity.service.impl;

import com.strong.common.exception.StrongException;
import com.strong.sercurity.entity.LoginUser;
import com.strong.sercurity.service.PasswordService;
import com.strong.system.entity.User;
import com.strong.system.service.UserService;
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
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userService.getUserByAccount(account);
        if (null == user) {
            log.info("登录用户：{} 不存在.", account);
            throw new StrongException();
        }

        passwordService.validate(user);
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(User user) {
        return new LoginUser(user.getId(), user.getDeptId(), user, null);
    }
}
