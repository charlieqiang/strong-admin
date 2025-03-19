package com.strong.system.vo;

import com.strong.api.system.dto.UserDto;
import com.strong.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author charlie
 * @date 2025/2/26 15:52
 **/
public class UserVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4034159858853752403L;

    private String account;
    private String username;
    private String avatar;
    private List<String> roles;

    public UserVo() {
    }

    public UserVo(UserDto user) {
        this.avatar = user.getAvatar();
        this.username = user.getUsername();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
