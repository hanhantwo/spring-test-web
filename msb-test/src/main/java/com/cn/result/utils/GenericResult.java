package com.cn.result.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: GenericResult
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:36
 */
@ApiModel(
        value = "GenericResult",
        description = "统一返回值"
)
public class GenericResult<T> extends AbstractResult {
    private static final long serialVersionUID = 1L;
    public static final GenericResult<Void> SUCCESS = new GenericResult<Void>() {
        private static final long serialVersionUID = -3815215167127265311L;

        public void setSuccess(boolean success) {
        }

        public void setCode(String code) {
        }

        public void setMsg(String msg) {
        }

        public void setData(Void data) {
        }
    };
    @ApiModelProperty(
            value = "业务数据",
            name = "data"
    )
    private T data;

    public GenericResult() {
    }

    public GenericResult(T data) {
        this.data = data;
        this.success = true;
        this.code = String.valueOf(CommonCode.CODE_SUCCESS.code());
        this.msg = CommonCode.CODE_SUCCESS.msg();
    }

    public static <T> GenericResult<T> failNoRetry(ResultCode code, String... msg) {
        return fail(code, false, msg);
    }

    public static <T> GenericResult<T> failNoRetry(String code, String... msg) {
        return fail(code, false, msg);
    }

    public static <T> GenericResult<T> fail(ResultCode code, String... msg) {
        return fail(code, true, msg);
    }

    public static <T> GenericResult<T> fail(ResultCode code, boolean retry, String... msg) {
        GenericResult<T> genericResult = new GenericResult();
        genericResult.setSuccess(false);
        genericResult.setCode(String.valueOf(code.code()));
        genericResult.setMsg(msg.length == 0 ? code.msg() : msg[0]);
        return genericResult;
    }

    public static <T> GenericResult<T> fail(String code, String... msg) {
        return fail(code, true, msg);
    }

    public static <T> GenericResult<T> fail(String code, boolean retry, String... msg) {
        GenericResult<T> genericResult = new GenericResult();
        genericResult.setSuccess(false);
        genericResult.setCode(code);
        genericResult.setMsg(msg.length == 0 ? null : msg[0]);
        return genericResult;
    }

    public static <T> GenericResult<T> success() {

        return success();
    }

    public static <T> GenericResult<T> success(T data) {
        GenericResult<T> genericResult = new GenericResult();
        genericResult.setSuccess(true);
        genericResult.setCode(String.valueOf(CommonCode.CODE_SUCCESS.code()));
        genericResult.setMsg(CommonCode.CODE_SUCCESS.msg());
        genericResult.setData(data);
        return genericResult;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public GenericResult put(Object key, Object value) {
        Map map = new HashMap();
        map.put(key, value);
        return success(map);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GenericResult{");
        sb.append("data=").append(this.data);
        sb.append(", success=").append(this.success);
        sb.append(", code='").append(this.code).append('\'');
        sb.append(", msg='").append(this.msg).append('\'');
        sb.append('}');
        return sb.toString();
    }

    static {
        SUCCESS.success = true;
        SUCCESS.code = String.valueOf(CommonCode.CODE_SUCCESS.code());
        SUCCESS.msg = CommonCode.CODE_SUCCESS.msg();
    }
}
