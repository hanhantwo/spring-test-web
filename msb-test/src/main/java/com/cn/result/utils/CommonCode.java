package com.cn.result.utils;

/**
 * @ClassName: CommonCode
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:21
 */
public enum CommonCode {
    CODE_SUCCESS(200, "success"),
    CODE_ACCEPTED(202, "accepted, usually to indicate part of success"),
    CODE_CONTENT_EMPTY(204, "content empty"),
    CODE_REQUEST_REDIRECT(302, "request redirect"),
    CODE_NO_LOGIN(401, "user not login"),
    CODE_PARAM_REQUIRED(402, "param required"),
    CODE_NO_AUTH(403, "no auth"),
    CODE_NOT_EXIST(404, "not exist"),
    CODE_VERSION_ERROR(406, "version error"),
    CODE_REQUEST_TIMEOUT(408, "request timeout"),
    CODE_ALREADY_EXIST(409, "already exist"),
    CODE_REQUEST_TOO_LARGE(413, "request too large"),
    CODE_GET_LOCK_ERROR(423, "get lock error"),
    CODE_USER_FROZEN(4111, "user has frozen"),
    CODE_USER_BLACKLIST(4112, "url in user blacklist"),
    CODE_USER_NEED_UPGRADE(4113, "user need upgrade"),
    CODE_PARAM_ERROR(4001, "param error"),
    CODE_SERVER_ERROR(500, "internal error"),
    CODE_SERVER_UNAVAILABLE(503, "service unavailable"),
    CODE_NOT_SUPPORT(415, "not supported"),
    CODE_IP_CHANGED(416, "ip changed"),
    CODE_COMMON_ERROR(5001, "common error"),
    CODE_THIRDPARTY_ERROR(5004, "third party error"),
    CODE_OPERATION_CANCELED(5005, "operation is canceled"),
    CODE_NOT_ENABLED(5006, "not enabled");

    private int code;
    private String msg;

    private CommonCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public static CommonCode of(int code) {
        CommonCode[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CommonCode commconCode = var1[var3];
            if (commconCode.code == code) {
                return commconCode;
            }
        }

        return null;
    }
}
