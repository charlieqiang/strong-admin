package com.strong.security.service.impl;

import com.strong.api.security.dto.LoginUserDto;
import com.strong.api.security.service.ApiSecurityService;
import com.strong.common.exception.CustomizeException;
import com.strong.security.entity.LoginUser;
import com.strong.security.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author charlie
 * @date 2025/3/6 18:01
 **/
@Service
public class ApiSecurityServiceImpl implements ApiSecurityService {
    @Override
    public LoginUserDto getLoginUser() {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getAuthentication().getPrincipal();
            if (loginUser == null) {
                return null;
            }
            LoginUserDto loginUserDto = new LoginUserDto();
            BeanUtils.copyProperties(loginUser, loginUserDto);
            return loginUserDto;
        } catch (Exception e) {
            throw new CustomizeException("获取用户信息异常");
        }
    }

    @Override
    public String encryptPassword(String password) {
        return SecurityUtils.encryptPassword(password);
    }
}
