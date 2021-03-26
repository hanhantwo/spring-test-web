package com.cn.result.utils;

import java.util.List;

/**
 * @ClassName: GenericPageResult
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:24
 */
public class GenericPageResult<T> extends AbstractResult {
    private static final long serialVersionUID = 1L;
    private Integer page;
    private Integer size;
    private Long totalNum;
    private Integer totalPage;
    private List<T> items;

    public GenericPageResult() {
    }

    public static <T> GenericPageResult<T> fail(ResultCode code, String... msg) {
        GenericPageResult<T> pageResult = new GenericPageResult();
        pageResult.setSuccess(false);
        pageResult.setCode(String.valueOf(code.code()));
        pageResult.setMsg(msg.length == 0 ? code.msg() : msg[0]);
        return pageResult;
    }

    public static <T> GenericPageResult<T> fail(String code, String... msg) {
        GenericPageResult<T> pageResult = new GenericPageResult();
        pageResult.setSuccess(false);
        pageResult.setCode(code);
        pageResult.setMsg(msg.length == 0 ? null : msg[0]);
        return pageResult;
    }

    public static <T> GenericPageResult<T> success(Pagination<T> page) {
        GenericPageResult<T> pageResult = new GenericPageResult();
        pageResult.setSuccess(true);
        pageResult.setCode(String.valueOf(CommonCode.CODE_SUCCESS.code()));
        pageResult.setMsg(CommonCode.CODE_SUCCESS.msg());
        pageResult.setPage(page.getPage());
        pageResult.setSize(page.getSize());
        pageResult.setTotalNum(page.getTotalNum());
        pageResult.setItems(page.getItems());
        pageResult.setTotalPage(page.getTotalPage());
        return pageResult;
    }

    public static <T> GenericPageResult<T> success(Integer page, Integer size, Long totalNum, List<T> items) {
        GenericPageResult<T> pageResult = new GenericPageResult();
        pageResult.setSuccess(true);
        pageResult.setCode(String.valueOf(CommonCode.CODE_SUCCESS.code()));
        pageResult.setMsg(CommonCode.CODE_SUCCESS.msg());
        pageResult.setPage(page);
        pageResult.setSize(size);
        pageResult.setTotalNum(totalNum);
        pageResult.setItems(items);
        pageResult.setTotalPage((int)((totalNum + (long)size - 1L) / (long)size));
        return pageResult;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
