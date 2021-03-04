package com.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Book
 * @Description TODO
 * @Author
 * @Date 2021/3/4 14:24
 */
@Slf4j
@Setter
@Getter
@TableName("book")
public class Book extends BaseEntity{
    private String bookName;
}
