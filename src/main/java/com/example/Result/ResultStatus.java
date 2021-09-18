package com.example.Result;

import lombok.Getter;
import lombok.Setter;

public enum ResultStatus {
    SUCCESS(0, "成功"),
    ERROR(1, "发生异常"),
    ERROR_PRAMATER_NULL(2, "非空参数鉴权失败"),
    ERROR_MOBILE_EXIST(3,"手机号已注册");

    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String msg;

    ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
