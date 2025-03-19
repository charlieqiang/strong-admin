package com.strong.system.param;

import com.strong.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author charlie
 * @date 2025/3/17 16:58
 **/
public class UserParam extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 674447841743810772L;

    private String username;
    private String account;
    private String password;
    private String avatar;
    private List<String> roleIdList;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
