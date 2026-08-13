package com.nana.common;

/**
 * 统一响应体。
 *
 * @param code    业务状态码（与 HTTP 状态码对齐，200 表示成功）
 * @param message 提示信息
 * @param data    数据载荷
 */
public record Result<T>(int code, String message, T data) {

    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> failure(int code, String message) {
        return new Result<>(code, message, null);
    }
}
