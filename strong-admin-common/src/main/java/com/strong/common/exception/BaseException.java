package com.strong.common.exception;

import com.strong.common.entity.result.ResultCode;

public abstract class BaseException extends RuntimeException implements ResultCode {
    private String msg;
    private int code;

    public abstract int defaultErrorCode();

    public abstract String defaultErrorMsg();

    public BaseException() {
        this.code = this.defaultErrorCode();
        this.msg = this.defaultErrorMsg();
    }

    public BaseException(int code, String msg) {
        super(msg, (Throwable) null, true, true);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    public BaseException(ResultCode resultCode, String str) {
        this(resultCode.getCode(), resultCode.getMsg() + ":" + str);
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
