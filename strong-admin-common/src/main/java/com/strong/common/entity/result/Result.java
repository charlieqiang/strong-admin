package com.strong.common.entity.result;

import com.strong.common.enums.SystemCodeEnum;

/**
 * @author charlie
 * @date 3/22/2023 7:22 PM
 **/
public class Result<T> extends BaseResult<T>{
    protected Result() {
    }

    protected Result(ResultCode resultCode) {
        super(resultCode);
    }

    protected Result(ResultCode resultCode, String msg) {
        super(resultCode, msg);
    }

    protected Result(int code, String msg) {
        super(code, msg);
    }

    protected Result(int code, String msg, T data) {
        super(code, msg, data);
    }

    protected Result(ResultCode resultCode, T data) {
        super(resultCode, data);
    }

    public static Result error(ResultCode resultCode) {
        return new Result(resultCode);
    }

    public static Result error(ResultCode resultCode, String msg) {
        return new Result(resultCode, msg);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result success() {
        return new Result(SystemCodeEnum.SUCCESS);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result(SystemCodeEnum.SUCCESS.getCode(), msg, data);
    }

    public static Result successMsg(String msg) {
        return new Result(SystemCodeEnum.SUCCESS.getCode(), msg, (Object)null);
    }

    public static <T> Result<T> success(T data) {
        return new Result(SystemCodeEnum.SUCCESS, data);
    }
}
