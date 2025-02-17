package com.strong.sercurity.param;

import java.io.Serializable;

/**
 * @author charlie
 * @date 2024/5/11 13:50
 **/
public class LoginParam implements Serializable {
    private static final long serialVersionUID = 1434106328032311860L;
    private String account;
    private String password;
    private String captchaId;
    private String captchaCode;

    public String getUsername() {
        return account;
    }

    public void setUsername(String username) {
        this.account = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
}
