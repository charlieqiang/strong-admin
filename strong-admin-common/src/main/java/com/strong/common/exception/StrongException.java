package com.strong.common.exception;

import com.strong.common.entity.result.ResultCode;

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

}
