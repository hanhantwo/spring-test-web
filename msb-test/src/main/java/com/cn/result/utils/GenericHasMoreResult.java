package com.cn.result.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GenericHasMoreResult
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:22
 */
public class GenericHasMoreResult<E> extends AbstractResult {
    private static final long serialVersionUID = 5930432881298200720L;
    private List<E> dataList;
    private boolean hasMore;
    private Map<String, Object> context;

    public static <E> GenericHasMoreResult<E> success() {
        return success(Collections.emptyList(), false);
    }

    public static <E> GenericHasMoreResult<E> success(List<E> dataList) {
        return success(dataList, false);
    }

    public static <E> GenericHasMoreResult<E> success(List<E> dataList, boolean hasMore) {
        GenericHasMoreResult<E> genericHasMoreResult = new GenericHasMoreResult();
        genericHasMoreResult.setSuccess(true);
        genericHasMoreResult.setCode(String.valueOf(CommonCode.CODE_SUCCESS.code()));
        genericHasMoreResult.setMsg(CommonCode.CODE_SUCCESS.msg());
        genericHasMoreResult.setDataList(dataList);
        genericHasMoreResult.setHasMore(hasMore);
        return genericHasMoreResult;
    }

    public static <E> GenericHasMoreResult<E> fail(String code, String... msg) {
        GenericHasMoreResult<E> genericHasMoreResult = new GenericHasMoreResult();
        genericHasMoreResult.setSuccess(false);
        genericHasMoreResult.setCode(code);
        genericHasMoreResult.setMsg(msg.length == 0 ? null : msg[0]);
        return genericHasMoreResult;
    }

    public List<E> getDataList() {
        return this.dataList;
    }

    public boolean isHasMore() {
        return this.hasMore;
    }

    public Map<String, Object> getContext() {
        return this.context;
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public GenericHasMoreResult() {
    }

    public String toString() {
        return "GenericHasMoreResult(super=" + super.toString() + ", dataList=" + this.getDataList() + ", hasMore=" + this.isHasMore() + ", context=" + this.getContext() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof GenericHasMoreResult)) {
            return false;
        } else {
            GenericHasMoreResult<?> other = (GenericHasMoreResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                label41: {
                    Object this$dataList = this.getDataList();
                    Object other$dataList = other.getDataList();
                    if (this$dataList == null) {
                        if (other$dataList == null) {
                            break label41;
                        }
                    } else if (this$dataList.equals(other$dataList)) {
                        break label41;
                    }

                    return false;
                }

                if (this.isHasMore() != other.isHasMore()) {
                    return false;
                } else {
                    Object this$context = this.getContext();
                    Object other$context = other.getContext();
                    if (this$context == null) {
                        if (other$context != null) {
                            return false;
                        }
                    } else if (!this$context.equals(other$context)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof GenericHasMoreResult;
    }

    public int hashCode() {
        Boolean PRIME = true;
        int result = super.hashCode();
        Object $dataList = this.getDataList();
        result = result * 59 + ($dataList == null ? 43 : $dataList.hashCode());
        result = result * 59 + (this.isHasMore() ? 79 : 97);
        Object $context = this.getContext();
        result = result * 59 + ($context == null ? 43 : $context.hashCode());
        return result;
    }
}
