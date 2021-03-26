package com.cn.result.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: HttpContextUtils
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:27
 */
public class HttpContextUtils {
    public HttpContextUtils() {
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
