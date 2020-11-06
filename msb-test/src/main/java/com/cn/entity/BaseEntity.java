package com.cn.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description TODO
 * @Author
 * @Date 2020/11/6 14:26
 */
@Setter
@Getter
public class BaseEntity implements Serializable{
    private Long id;
    private Date createTime;
    private Date updateTime;

    public void setDate(){
        Date date = new Date();
        this.createTime=date;
        this.updateTime=date;
    }

}
