package com.strong.security.service.impl;

import com.strong.api.system.dto.UserDto;
import com.strong.common.enums.SystemCodeEnum;
import com.strong.common.exception.CustomizeException;
import com.strong.security.context.AuthenticationContextHolder;
import com.strong.security.service.PasswordService;
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

    public void validate(UserDto user) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        if (!matches(user, password)) {
            throw new CustomizeException(SystemCodeEnum.LOGIN_MSG_ERROR);
        }
    }

    public boolean matches(UserDto user, String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
