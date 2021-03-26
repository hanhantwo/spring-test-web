package com.cn.quartz.controller;

import com.cn.entity.common.annotation.Log;
import com.cn.entity.common.core.controller.BaseController;
import com.cn.entity.common.core.page.TableDataInfo;
import com.cn.entity.common.enums.BusinessType;
import com.cn.quartz.domain.SysJobLog;
import com.cn.quartz.service.ISysJobLogService;
import com.cn.result.utils.GenericResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度日志操作处理
 * 
 * @author zhanghongjun
 */
@RestController
@RequestMapping("/monitor/jobLog")
@Api(tags = "调度日志操作处理")
public class SysJobLogController extends BaseController
{
    @Autowired
    private ISysJobLogService jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:list')")
    @GetMapping("/list")
    @ApiOperation("查询定时任务调度日志列表")
    public GenericResult<TableDataInfo> list(SysJobLog sysJobLog)
    {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        return GenericResult.success(getDataTable(list));
    }

    /**
     * 导出定时任务调度日志列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:export')")
    @Log(title = "任务调度日志", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation("导出定时任务调度日志列表")
    public GenericResult export(SysJobLog sysJobLog)
    {
//        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
//        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
//        return util.exportExcel(list, "调度日志");
        return null;
    }
    
    /**
     * 根据调度编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:query')")
    @GetMapping(value = "/{configId}")
    @ApiOperation("根据调度编号获取详细信息")
    public GenericResult getInfo(@PathVariable Long jobLogId)
    {
        return GenericResult.success(jobLogService.selectJobLogById(jobLogId));
    }


    /**
     * 删除定时任务调度日志
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
    @Log(title = "定时任务调度日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobLogIds}")
    @ApiOperation("删除定时任务调度日志")
    public GenericResult remove(@PathVariable Long[] jobLogIds)
    {
        return toAjax(jobLogService.deleteJobLogByIds(jobLogIds));
    }

    /**
     * 清空定时任务调度日志
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    @ApiOperation("清空定时任务调度日志")
    public GenericResult clean()
    {
        jobLogService.cleanJobLog();
        return GenericResult.success();
    }
}
