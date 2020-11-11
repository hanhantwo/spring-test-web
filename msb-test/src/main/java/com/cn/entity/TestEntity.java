package com.cn.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName TestEntity
 * @Description TODO
 * @Author
 * @Date 2020/11/6 14:25
 */
@Setter
@Getter
//@TableName("test_entity")
public class TestEntity extends BaseEntity {

    private String name;
    private Long age;
}
