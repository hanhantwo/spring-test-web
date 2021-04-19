package com.cn.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName: NormHistoryExcelListVo
 * @Description: excel实体类
 * @Author: zhanghongjun
 * @Date: 2021/4/19 14:40
 */
@Setter
@Getter
public class NormHistoryExcelListVo {
    /**
     * id
     */
    private Long id;
    /**
     * 考核指标名称
     */
    private String normName;

    /**
     * 指标分数
     */
    private  int  score;

    /**
     * 处理对象
     */
    private String obj;

    /**
     * 是否可用（0代表初始 1代表可用于计算分数（超过申诉时间，不能撤销））
     */
    private String available;

    /**
     * 发生日期
     */
    private Date date;
    /**
     * 详细描述
     */
    private String description;


}
