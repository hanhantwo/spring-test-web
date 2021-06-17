package com.cn.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.entity.NormHistoryExcelListVo;
import com.cn.poi.ExcelUtil;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: ReportExcelController
 * @Description: 导出Excel表
 * @Author: zhanghongjun
 * @Date: 2021/4/19 16:34
 */
@Slf4j
@RestController
public class ReportExcelController {
	@PostMapping("/doExportExcel")
	@ApiOperation("excel导出")
	public void doExportExcel(HttpServletResponse response) throws Exception {

		List<NormHistoryExcelListVo> list = null;
		ExcelUtil<NormHistoryExcelListVo> util = new ExcelUtil<>(NormHistoryExcelListVo.class);
		util.exportExcel2(list, "excel文件名称", response);
	}
}
