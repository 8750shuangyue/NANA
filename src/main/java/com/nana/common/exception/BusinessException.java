package com.nana.common.exception;

/**
 * 业务异常，抛出后由 {@link GlobalExceptionHandler} 统一处理。
 */
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    public int getCode() {
        return code;
    }
}
