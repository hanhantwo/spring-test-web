package com.cn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author
 * @Date 2021/2/25 9:27
 */
@Slf4j
@RestController
public class TestController {
    /**
     * 文件上传 方法1
     *
     * @param file
     */
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        //要上传的文件路径：一般是服务器文件夹地址
        String upAddress = "F:\\workSpace\\spring-test-web\\doc";
        if (file == null) {
            return null;
        }
        //获取文件名称
        String fileName = file.getOriginalFilename();
        File dir = new File(upAddress);
        //先判断路径地址是否存在，不存在就创建
        if (!dir.exists()) {
            dir.mkdir();
        }
        //生成中很可能重新生成文件名称，就需要截取后缀名
        //截取前缀名
        String prefixFileName = fileName.substring(0, fileName.indexOf("."));

        //截取后缀名
        String suffixFileName = fileName.substring(fileName.lastIndexOf("."));

        //这里可以随机生成一个文件名+后缀
        String newFileName = "文件名称" + suffixFileName;

        //保存文件
        File saveFile = new File(upAddress + newFileName);
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("保存文件失败");
        }

        return newFileName;
    }
    /**
     * 上传附件
     */
    public Integer uploadFile(MultipartFile file, HttpServletRequest request) {
        try {
            //获取原始文件名
            String fileName = file.getOriginalFilename();
            //截取文件后缀
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //文件名
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
            //保存到服务器的路径
            String path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF\\classes\\static\\approval\\";
            //文件上传到服务器
            SaveFileFromInputStream(file.getInputStream(), path, fileName);
        } catch (IOException e) {
            System.out.println(e);
        }
        return 1;
    }
    /**
     *
     * @param stream 文件输入流
     * @param path 保存的文件地址
     * @param savefile 文件名
     * @throws IOException
     */
    public static void SaveFileFromInputStream(InputStream stream, String path, String savefile) throws IOException {
        FileOutputStream fs = new FileOutputStream(path + savefile);
        byte[] buffer = new byte[1024 * 1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }
    /**
     * @param res
     * @param fileName 文件名称
     * @param filePath 文件地址
     * @Description 文件下载
     */
    public static void download(HttpServletResponse res, String fileName, String filePath) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setCharacterEncoding("utf-8");
        //todo 设置响应头，返回文件名称
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            //todo: 通过文件地址加名称拿取文件
            bis = new BufferedInputStream(new FileInputStream(new File(filePath + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    /**
     * 删除文件
     *
     * @param filePath 文件完整路径（包括文件名称）
     * @return
     */
    public static boolean deleteFile(String filePath) {
        boolean delete_flag = false;
        File file = new File(filePath);
        if (file.exists() && file.isFile() && file.delete()) {
            delete_flag = true;
        } else {
            delete_flag = false;
        }
        return delete_flag;
    }
}
