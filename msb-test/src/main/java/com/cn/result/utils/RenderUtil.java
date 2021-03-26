package com.cn.result.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: RenderUtil
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:28
 */
public class RenderUtil {
    public RenderUtil() {
    }

    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
