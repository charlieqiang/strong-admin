package com.strong.security.vo;

import java.io.Serializable;

/**
 * @author charlie
 * @date 2024/5/1 10:26
 **/
public class CaptchaVo implements Serializable {
    private static final long serialVersionUID = -1507136866487804605L;

    private String captchaId;
    private String captchaImg;

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaImg() {
        return captchaImg;
    }

    public void setCaptchaImg(String captchaImg) {
        this.captchaImg = captchaImg;
    }
}
