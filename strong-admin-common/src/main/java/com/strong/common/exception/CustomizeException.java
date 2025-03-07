package com.strong.common.exception;

import com.strong.common.entity.result.ResultCode;
import com.strong.common.enums.SystemCodeEnum;

public class CustomizeException extends BaseException {
    public int defaultErrorCode() {
        return SystemCodeEnum.OPERATE_ERROR.getCode();
    }

    public String defaultErrorMsg() {
        return SystemCodeEnum.OPERATE_ERROR.getMsg();
    }

    public CustomizeException() {
    }

    public CustomizeException(String msg) {
        super(SystemCodeEnum.OPERATE_ERROR, msg);
    }

    public CustomizeException(int code, String msg) {
        super(code, msg);
    }

    public CustomizeException(ResultCode resultCode) {
        super(resultCode);
    }

    public CustomizeException(ResultCode resultCode, String str) {
        super(resultCode, str);
    }

}
