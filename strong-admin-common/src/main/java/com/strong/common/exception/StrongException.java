package com.strong.common.exception;

import com.strong.common.entity.result.ResultCode;
import com.strong.common.enums.SystemCodeEnum;

public class StrongException extends BusinessException {

    public StrongException() {
        super();
    }

    public StrongException(int code, String msg) {
        super(code, msg);
    }

    public StrongException(ResultCode resultCode) {
        super(resultCode);
    }

    public StrongException(ResultCode resultCode, String str) {
        super(resultCode, str);
    }

    public StrongException(String str) {
        super(SystemCodeEnum.OPERATE_ERROR, str);
    }
}
