package com.cn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(type = IdType.AUTO)
    private Long id;
    private Date createTime;
    private Date updateTime;

    /**
     * 封装时间赋值方法
     */
    public void setDate(){
        Date date = new Date();
        this.createTime=date;
        this.updateTime=date;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
