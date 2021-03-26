package com.cn.system.domain.vo;
import com.cn.entity.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 参数配置表 查询
 * 
 * @author zhanghongjun
 */
@ApiModel("查詢Vo類")
public class SysConfigQuery
{
    private static final long serialVersionUID = 2409035569001869098L;

    /** 参数名称 */
    @Excel(name = "参数名称")
    @ApiModelProperty("参数名称")
    private String configName;

    /** 系统内置（Y是 N否） */
    @ApiModelProperty("系统内置（Y是 N否）")
    private String configType;

    /** 参数键名 */
    @ApiModelProperty("参数键名")
    private String configKey;

    /** 开始时间 */
    @ApiModelProperty("开始时间")
    private String beginTime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    private String endTime;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
