package com.strong.sercurity.vo;

import java.io.Serializable;

/**
 * @author charlie
 * @date 2024/5/1 10:26
 **/
public class TokenVo implements Serializable {
    private static final long serialVersionUID = 2527707019208926912L;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenVo(String token) {
        this.token = token;
    }
}
