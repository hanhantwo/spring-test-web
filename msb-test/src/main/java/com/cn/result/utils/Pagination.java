package com.cn.result.utils;

import java.util.List;

/**
 * @ClassName: Pagination
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:25
 */
public class Pagination<T> {
    private Integer page;
    private Integer size;
    private Long totalNum;
    private Integer totalPage;
    private List<T> items;

    public Pagination(Integer page, Integer size, Long totalNum, List<T> items) {
        this.page = page;
        this.size = size;
        this.totalNum = totalNum;
        this.items = items;
        this.totalPage = (int)((totalNum + (long)size - 1L) / (long)size);
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
