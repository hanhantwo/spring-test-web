package com.cn.result.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName: ResultList
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:26
 */
@ApiModel(
        value = "ResultList",
        description = "数组"
)
public class ResultList<T> {
    @ApiModelProperty(
            value = "数组",
            name = "list"
    )
    private List<T> list;

    public ResultList() {
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResultList)) {
            return false;
        } else {
            ResultList<?> other = (ResultList)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$list = this.getList();
                Object other$list = other.getList();
                if (this$list == null) {
                    if (other$list != null) {
                        return false;
                    }
                } else if (!this$list.equals(other$list)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResultList;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $list = this.getList();
         result = result * 59 + ($list == null ? 43 : $list.hashCode());
        return result;
    }

    public String toString() {
        return "ResultList(list=" + this.getList() + ")";
    }
}
