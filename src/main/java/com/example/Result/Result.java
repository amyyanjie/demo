package com.example.Result;

import lombok.Getter;
import lombok.Setter;

public class Result {
    //若属性不添加getter/setter，作为返回封装，会报No converter found for return value of type ...Result class...
    @Getter@Setter
    private int code;
    @Getter@Setter
    private String msg;
    @Getter@Setter
    private Object data;

    private Result(ResultStatus status, Object data) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.data = data;
    }

    public static Result ok() {
        return new Result(ResultStatus.SUCCESS, null);
    }

    public static Result ok(Object data) {
        return new Result(ResultStatus.SUCCESS, data);
    }

    public static Result error(ResultStatus status) {
        return new Result(status, null);
    }

    public static Result error(String msg) {
        ResultStatus status = ResultStatus.ERROR;
        status.setMsg(msg);
        return error(status);
    }
}
