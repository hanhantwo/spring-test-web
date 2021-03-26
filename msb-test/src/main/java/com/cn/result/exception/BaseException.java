package com.cn.result.exception;

/**
 * @ClassName: BaseException
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:41
 */
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;

    public BaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BaseException(ErrorCode errorCode) {
        this(errorCode.getDescription(), errorCode);
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
