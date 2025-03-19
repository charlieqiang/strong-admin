package com.strong.api.security.dto;

import com.strong.api.system.dto.UserDto;

import java.io.Serializable;

/**
 * @author charlie
 * @date 2025/3/6 17:58
 **/
public class LoginUserDto implements Serializable {

    private static final long serialVersionUID = 1846662914402683964L;

    private String userId;

    private String tokenId;

    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public UserDto getUserDto() {
        return user;
    }

    public void setUserDto(UserDto user) {
        this.user = user;
    }
}
