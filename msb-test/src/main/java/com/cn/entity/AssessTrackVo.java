package com.cn.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 关于树结构知识储备
 */
@Setter
@Getter
public class AssessTrackVo {

    /**
     * id
     */
    private Long id;

    /**
     * 上级指标id
     */
    private Long parentId;
    /**
     * 父亲对象
     */
    private AssessTrackVo parentATV;
    /**
     * 名称
     */
    private String name;

    /**
     * 权重
     */
    private Integer weight;
    /**
     * 实际得分
     */
    private Integer actualScore;
    /**
     * 颜色标记
     */
    private Integer colorCode;
    /**
     * 子集
     */
    List<AssessTrackVo> children;

    @Override
    public String toString() {
        return "AssessTrackVo{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", parentATV=" + parentATV +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", actualScore=" + actualScore +
                ", colorCode=" + colorCode +
                '}';
    }
}
