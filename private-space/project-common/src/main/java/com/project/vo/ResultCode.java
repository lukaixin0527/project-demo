package com.project.vo;

import lombok.Getter;

/**
 * @ClassName ResultCode
 * @Description todo 响应码枚举
 * @Author lu
 * @Date 2020/4/9
 * @Version 1.0
 */
@Getter
public enum ResultCode {

    SUCCESS(1000, "操作成功"),

    FAILED(1001, "操作失败"),

    VALIDATE_PARAMETER_FAILED(1002, "入参校验失败"),

    VALIDATE_DATA_FAILED(1003, "接口签名验证失败"),

    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
