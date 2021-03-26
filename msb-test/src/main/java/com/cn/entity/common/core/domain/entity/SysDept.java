package com.cn.entity.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门表 sys_dept
 * 
 * @author zhanghongjun
 */
@ApiModel("部门实体")
@Setter
@Getter
public class SysDept extends SysBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    @ApiModelProperty("父部门ID")
    private Long parentId;

    /** 祖级列表 */
    @ApiModelProperty("祖级列表")
    private String ancestors;

    /** 部门名称 */
    @ApiModelProperty("部门名称")
    private String deptName;

    /** 显示顺序 */
    @ApiModelProperty("显示顺序")
    private String orderNum;

    /** 负责人 */
    @ApiModelProperty("负责人")
    private String leader;

    /** 联系电话 */
    @ApiModelProperty("联系电话")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty("邮箱")
    private String email;

    /** 部门状态:0正常,1停用 */
    @ApiModelProperty("部门状态:0正常,1停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /** 父部门名称 */
    @ApiModelProperty("父部门名称")
    private String parentName;
    
    /** 子部门 */
    @ApiModelProperty("子部门")
    private List<SysDept> children = new ArrayList<SysDept>();


}
