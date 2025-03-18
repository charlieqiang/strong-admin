package com.strong.system.vo;

import com.strong.api.system.dto.UserDto;
import com.strong.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author charlie
 * @date 2025/2/26 15:52
 **/
public class UserInfoVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4034159858853752403L;

    private List<String> roles;
    private String account;
    private String avatar;
    private String name;

    public UserInfoVo() {
    }

    public UserInfoVo(UserDto user) {
        this.avatar = user.getAvatar();
        this.name = user.getUsername();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
