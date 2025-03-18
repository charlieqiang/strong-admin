package com.strong.system.param;

import java.io.Serializable;

/**
 * @author charlie
 * @date 2025/3/17 16:58
 **/
public class UserParam implements Serializable {
    private static final long serialVersionUID = 674447841743810772L;

    private String username;
    private String account;

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
