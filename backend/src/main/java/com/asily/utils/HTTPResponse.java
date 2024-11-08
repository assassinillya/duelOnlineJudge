package com.asily.utils;

import lombok.Data;

@Data
public class HTTPResponse<T> {

    private int code;  // HTTP 状态码
    private T data;    // 响应数据
    private String msg; // 响应信息

    // 构造函数
    public HTTPResponse(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    // 成功响应
    public static <T> HTTPResponse<T> success(T data) {
        return new HTTPResponse<>(200, data, "success");
    }

    // 成功响应，无数据
    public static <T> HTTPResponse<T> success() {
        return success(null);
    }

    // 未授权
    public static <T> HTTPResponse<T> unauthorized(String msg) {
        return new HTTPResponse<>(401, null, msg);
    }

    // 禁止访问
    public static <T> HTTPResponse<T> forbidden(String msg) {
        return new HTTPResponse<>(403, null, msg);
    }

    // 请求失败
    public static <T> HTTPResponse<T> failure(int code, String msg) {
        return new HTTPResponse<>(code, null, msg);
    }

    // 服务器内部错误
    public static <T> HTTPResponse<T> internalError(String msg) {
        return new HTTPResponse<>(500, null, msg);
    }

    // 数据不存在
    public static <T> HTTPResponse<T> notFound(String msg) {
        return new HTTPResponse<>(404, null, msg);
    }
}
