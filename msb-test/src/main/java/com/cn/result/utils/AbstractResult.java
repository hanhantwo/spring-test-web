package com.cn.result.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: AbstractResult
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:20
 */
@ApiModel(
        value = "result",
        description = "统一返回值"
)
public abstract class AbstractResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            value = "是否处理成功",
            name = "success"
    )
    protected boolean success;
    @ApiModelProperty(
            value = "返回的code码",
            name = "code"
    )
    protected String code;
    @ApiModelProperty(
            value = "返回的提示信息",
            name = "msg"
    )
    protected String msg;

    public AbstractResult() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
