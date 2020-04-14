package com.project.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ServerResult
 * @Description todo 服务端返回Result封装
 * @Author lu
 * @Date 2020/4/9
 * @Version 1.0
 */
@Data
public class ServerResult<T> implements Serializable {

    /**
     * 返回结果
     */
    private T data;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;


    /**
     * 调用成功
     */
    public static <T> ServerResult<T> isSuccess(T data, int code, String msg) {
        ServerResult serverResult = new ServerResult();
        serverResult.setData(data);
        serverResult.setCode(code);
        serverResult.setMsg(msg);
        return serverResult;
    }

    /**
     * 调用失败
     */
    public static <T> ServerResult<T> isFailure(int code, String msg) {
        ServerResult serverResult = new ServerResult();
        serverResult.setData(null);
        serverResult.setCode(code);
        serverResult.setMsg(msg);
        return serverResult;
    }
}
