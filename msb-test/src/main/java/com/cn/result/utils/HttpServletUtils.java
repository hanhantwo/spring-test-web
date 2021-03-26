package com.cn.result.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: HttpServletUtils
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:28
 */
public class HttpServletUtils {
    public HttpServletUtils() {
    }

    public static boolean jsAjax(HttpServletRequest req) {
        boolean isAjaxRequest = false;
        if (!StringUtils.isBlank(req.getHeader("x-requested-with")) && req.getHeader("x-requested-with").equals("XMLHttpRequest")) {
            isAjaxRequest = true;
        }

        return isAjaxRequest;
    }
}
