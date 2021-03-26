package com.cn.result.utils;

import java.util.List;

/**
 * @ClassName: GenericListResult
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:23
 */
public class GenericListResult<T> extends AbstractResult {
    private static final long serialVersionUID = 1L;
    private List<T> items;

    public GenericListResult() {
    }

    public GenericListResult(List<T> items) {
        this.items = items;
        this.success = true;
        this.code = String.valueOf(CommonCode.CODE_SUCCESS.code());
        this.msg = CommonCode.CODE_SUCCESS.msg();
    }

    public static <T> GenericListResult<T> fail(ResultCode code, String... msg) {
        return fail(code, true, msg);
    }

    public static <T> GenericListResult<T> fail(ResultCode code, boolean retry, String... msg) {
        GenericListResult<T> genericResult = new GenericListResult();
        genericResult.setSuccess(false);
        genericResult.setCode(String.valueOf(code.code()));
        genericResult.setMsg(msg.length == 0 ? code.msg() : msg[0]);
        return genericResult;
    }

    public static <T> GenericListResult<T> fail(String code, String... msg) {
        GenericListResult<T> genericResult = new GenericListResult();
        genericResult.setSuccess(false);
        genericResult.setCode(code);
        genericResult.setMsg(msg.length == 0 ? null : msg[0]);
        return genericResult;
    }

    public static <T> GenericListResult<T> success() {
        return success((List)null);
    }

    public static <T> GenericListResult<T> success(List<T> items) {
        GenericListResult<T> genericResult = new GenericListResult();
        genericResult.setSuccess(true);
        genericResult.setCode(String.valueOf(CommonCode.CODE_SUCCESS.code()));
        genericResult.setMsg(CommonCode.CODE_SUCCESS.msg());
        genericResult.setItems(items);
        return genericResult;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return this.items;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GenericListResult{");
        sb.append("items=").append(this.items);
        sb.append(", success=").append(this.success);
        sb.append(", code='").append(this.code).append('\'');
        sb.append(", msg='").append(this.msg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
