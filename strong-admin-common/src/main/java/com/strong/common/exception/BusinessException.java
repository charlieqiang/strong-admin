package com.strong.common.exception;

import com.strong.common.entity.result.ResultCode;
import com.strong.common.enums.SystemCodeEnum;

public class BusinessException extends BaseException {
    public int defaultErrorCode() {
        return SystemCodeEnum.OPERATE_ERROR.getCode();
    }

    public String defaultErrorMsg() {
        return SystemCodeEnum.OPERATE_ERROR.getMsg();
    }

    public BusinessException() {
    }

    public BusinessException(int code, String msg) {
        super(code, msg);
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode);
    }

    public BusinessException(ResultCode resultCode, String str) {
        super(resultCode, str);
    }

}
