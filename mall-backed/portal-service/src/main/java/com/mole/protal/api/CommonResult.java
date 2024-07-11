package com.mole.protal.api;

import lombok.Data;

/**
 * 通用返回结果类
 */
@Data
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommonResult() {
    }

    public CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功静态方法
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(200, "操作成功", data);
    }

    public static <T> CommonResult<T> success(String message, T data) {
        return new CommonResult<>(200, message, data);
    }

    // 失败静态方法
    public static <T> CommonResult<T> failed() {
        return new CommonResult<>(500, "操作失败", null);
    }

    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(500, message, null);
    }
}
