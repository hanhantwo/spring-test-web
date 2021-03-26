package com.cn.result.exception;

/**
 * @ClassName: ErrorCode
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:30
 */
public interface ErrorCode {
    ErrorType getErrorType();

    ErrorLevel getErrorLevel();

    String getCode();

    String getDescription();
}
