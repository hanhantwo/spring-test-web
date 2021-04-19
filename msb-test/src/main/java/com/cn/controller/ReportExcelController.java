package com.cn.controller;

import com.cn.entity.NormHistoryExcelListVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
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

        HSSFWorkbook wb = new HSSFWorkbook();//创建HSSFWorkbook对象

        HSSFSheet sheet=wb.createSheet("成绩表");//建立sheet对象

        HSSFRow row1=sheet.createRow(0); //在sheet里创建第一行，参数为行索引

        HSSFCell cell=row1.createCell(0); //创建单元格

        cell.setCellValue("当月记录历史信息"); //设置单元格内容

        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        //在sheet里创建第二行

        HSSFRow row2=sheet.createRow(1);

        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("序号");
        row2.createCell(1).setCellValue("指标姓名");
        row2.createCell(2).setCellValue("分值");
        row2.createCell(3).setCellValue("处理对象");
        row2.createCell(4).setCellValue("状态");
        row2.createCell(5).setCellValue("发生时间");
        row2.createCell(6).setCellValue("详细描述");

        //在sheet里创建第三行
        if(list!=null&&list.size()>0){
            for (int i = 0; i <list.size() ; i++) {
                HSSFRow row3=sheet.createRow(2+i);
                row3.createCell(0).setCellValue(list.get(i).getId());
                row3.createCell(1).setCellValue(list.get(i).getNormName());
                row3.createCell(2).setCellValue(list.get(i).getScore());
                row3.createCell(3).setCellValue(list.get(i).getObj());
                row3.createCell(4).setCellValue((list.get(i).getAvailable()=="1")?"已生效":"未生效");
                row3.createCell(5).setCellValue(list.get(i).getDate());
                row3.createCell(6).setCellValue(list.get(i).getDescription());
            }
        }
        //输出Excel文件

        OutputStream output=response.getOutputStream();

        response.reset();

        //设置响应头，
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("当月记录历史信息", "utf-8"));

        wb.write(output);

        output.close();

    }
}
