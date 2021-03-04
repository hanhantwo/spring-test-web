package com.cn.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @ClassName TestEntity
 * @Description TODO
 * @Author
 * @Date 2020/11/6 14:25
 */
@Setter
@Getter
@TableName("test_entity")
public class TestEntity extends BaseEntity {

    @NotBlank(message = "名称不能为空")
    private String name;
    private Long age;
    @TableField(exist = false)
    private List<Book> bookList;
}
