package com.strong.sercurity.service.impl;

import com.strong.common.exception.StrongException;
import com.strong.sercurity.context.AuthenticationContextHolder;
import com.strong.sercurity.service.PasswordService;
import com.strong.system.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 登录密码方法
 *
 * @author ruoyi
 */
@Service
public class PasswordServiceImpl implements PasswordService {

    public void validate(User user) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        if (!matches(user, password)) {
            throw new StrongException("密码错误");
        }
    }

    public boolean matches(User user, String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
