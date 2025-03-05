package com.strong.common.enums;

import com.strong.common.entity.result.ResultCode;

/**
 * @author charlie
 * @date 3/22/2023 7:27 PM
 **/
public enum SystemCodeEnum implements ResultCode {
    SUCCESS(200, "操作成功"),
    OPERATE_ERROR(201, "操作失败"),
    ILLEGAL_TOKEN(508, "非法令牌"),
    OTHER_CLIENTS(512, "跨域请求"),
    TOKEN_EXPIRED(512, "令牌失效"),
    LOGIN_MSG_ERROR(624, "账号或密码错误"),
    ;

    private final int code;
    private final String msg;

    private SystemCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public static String getNameByCode(Integer code) {
        if (code == null) {
            return null;
        } else {
            SystemCodeEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                SystemCodeEnum systemCodeEnum = var1[var3];
                if (code == systemCodeEnum.getCode()) {
                    return systemCodeEnum.getMsg();
                }
            }

            return null;
        }
    }

    public static SystemCodeEnum getEnumByCode(Integer code) {
        if (code == null) {
            return null;
        } else {
            SystemCodeEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                SystemCodeEnum systemCodeEnum = var1[var3];
                if (code == systemCodeEnum.getCode()) {
                    return systemCodeEnum;
                }
            }

            return null;
        }
    }
}
