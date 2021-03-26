package com.cn.result.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @ClassName: CommonErrorCode
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:42
 */
public enum CommonErrorCode implements ErrorCode {
    USER_ERROR(ErrorType.USER, ErrorLevel.INFO, "100000", "用户操作异常"),
    BIZ_ERROR(ErrorType.BIZ, ErrorLevel.WARN, "200001", "业务异常"),
    SYSTEM_ERROR(ErrorType.SYSTEM, ErrorLevel.ERROR, "400000", "业务异常"),
    TOKEN_IS_NULL(ErrorType.BIZ, ErrorLevel.INFO, "401", "您还未登录,请先登录"),
    TOKEN_INVALID(ErrorType.BIZ, ErrorLevel.INFO, "401", "您还未登录,请先登录"),
    UPDATE_TOKEN_FAIL(ErrorType.BIZ, ErrorLevel.ERROR, "300000", "更新token异常"),
    SYSTEM_EXCEPTION(ErrorType.SYSTEM, ErrorLevel.ERROR, "0000000", "系统异常");

    private final ErrorType errorType;
    private final ErrorLevel errorLevel;
    private final String code;
    private final String description;

    private CommonErrorCode(ErrorType errorType, ErrorLevel errorLevel, String code, String description) {
        this.errorLevel = errorLevel;
        this.errorType = errorType;
        this.code = code;
        this.description = description;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public ErrorLevel getErrorLevel() {
        return this.errorLevel;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
