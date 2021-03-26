package com.cn.quartz.controller;

import com.cn.entity.common.annotation.Log;
import com.cn.entity.common.core.controller.BaseController;
import com.cn.entity.common.core.page.TableDataInfo;
import com.cn.entity.common.enums.BusinessType;
import com.cn.entity.common.exception.job.TaskException;
import com.cn.entity.common.utils.SecurityUtils;
import com.cn.quartz.domain.SysJob;
import com.cn.quartz.service.ISysJobService;
import com.cn.quartz.util.CronUtils;
import com.cn.result.exception.CommonErrorCode;
import com.cn.result.utils.GenericResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度任务信息操作处理
 * 
 * @author zhanghongjun
 */
@RestController
@RequestMapping("/monitor/job")
@Api(tags = "调度任务信息操作处理")
public class SysJobController extends BaseController
{
    @Autowired
    private ISysJobService jobService;

    /**
     * 查询定时任务列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:list')")
    @GetMapping("/list")
    @ApiOperation("查询定时任务列表")
    public GenericResult<TableDataInfo> list(SysJob sysJob)
    {
        startPage();
        List<SysJob> list = jobService.selectJobList(sysJob);
        return GenericResult.success(getDataTable(list));
    }

    /**
     * 导出定时任务列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:export')")
    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ApiOperation("导出定时任务列表")
    public GenericResult export(SysJob sysJob)
    {
//        List<SysJob> list = jobService.selectJobList(sysJob);
//        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
//        return util.exportExcel(list, "定时任务");
        return null;
    }

    /**
     * 获取定时任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:query')")
    @GetMapping(value = "/{jobId}")
    @ApiOperation("获取定时任务详细信息")
    public GenericResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return GenericResult.success(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:add')")
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增定时任务")
    public GenericResult add(@RequestBody SysJob sysJob) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(sysJob.getCronExpression()))
        {
            return GenericResult.fail(CommonErrorCode.BIZ_ERROR.getCode(),"cron表达式不正确");
        }
        sysJob.setCreateBy(100l);
        return toAjax(jobService.insertJob(sysJob));
    }

    /**
     * 修改定时任务
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:edit')")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改定时任务")
    public GenericResult edit(@RequestBody SysJob sysJob) throws SchedulerException, TaskException
    {
        if (!CronUtils.isValid(sysJob.getCronExpression()))
        {
            return GenericResult.fail(CommonErrorCode.BIZ_ERROR.getCode(),"cron表达式不正确");
        }
        //设置当前用户ID
        sysJob.setUpdateBy(SecurityUtils.getLoginUser().getUser().getUserId());
        return toAjax(jobService.updateJob(sysJob));
    }

    /**
     * 定时任务状态修改
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    @ApiOperation("定时任务状态修改")
    public GenericResult changeStatus(@RequestBody SysJob job) throws SchedulerException
    {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 定时任务立即执行一次
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:changeStatus')")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/run")
    @ApiOperation("定时任务立即执行一次")
    public GenericResult run(@RequestBody SysJob job) throws SchedulerException
    {
        jobService.run(job);
        return GenericResult.success();
    }

    /**
     * 删除定时任务
     */
    @PreAuthorize("@ss.hasPermi('monitor:job:remove')")
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobIds}")
    @ApiOperation("删除定时任务")
    public GenericResult remove(@PathVariable Long[] jobIds) throws SchedulerException, TaskException
    {
        jobService.deleteJobByIds(jobIds);
        return GenericResult.success();
    }
}
