package com.strong.security.build;

import com.strong.api.security.dto.LoginUserDto;
import com.strong.security.entity.LoginUser;

/**
 * @author charlie
 * @date 2025/4/28 13:30
 **/
public class LoginUserToDtoBuilder {

    private LoginUserToDtoBuilder() {
        // 私有构造器，工具类不允许实例化
    }

    public static LoginUserDto build(LoginUser loginUser) {
        if (loginUser == null) {
            return null;
        }

        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUserId(loginUser.getUserId());
        loginUserDto.setTokenId(loginUser.getTokenId());
        loginUserDto.setUser(loginUser.getUserDto());  // UserDto 在 LoginUser 中直接有

        return loginUserDto;
    }
}
