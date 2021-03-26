package com.cn.result.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @ClassName: IPUtil
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:28
 */
public class IPUtil {
    private static final Logger log = LoggerFactory.getLogger(IPUtil.class);

    public IPUtil() {
    }

    public static InetAddress getLocalIP() {
        InetAddress inetAddress = null;

        try {
            if (OSUtils.getOSType() == OSUtils.OS.linux.windows) {
                inetAddress = InetAddress.getLocalHost();
            } else {
                Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();

                label39:
                while(true) {
                    while(true) {
                        if (!netInterfaces.hasMoreElements()) {
                            break label39;
                        }

                        NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
                        Enumeration inetAddresses = ni.getInetAddresses();

                        while(inetAddresses.hasMoreElements()) {
                            InetAddress tmpInetAddress = (InetAddress)inetAddresses.nextElement();
                            if (tmpInetAddress.isSiteLocalAddress() && !tmpInetAddress.isLoopbackAddress() && tmpInetAddress.getHostAddress().indexOf(":") == -1) {
                                inetAddress = tmpInetAddress;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception var5) {
            throw new IllegalStateException("can not find ip of this machine", var5);
        }

        if (inetAddress != null) {
            return inetAddress;
        } else {
            throw new IllegalStateException("can not find ip of this machine");
        }
    }

    public static String getAllIP(HttpServletRequest request) {
        String ip = request.getHeader("CF-Connecting-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("cf-connecting-ip");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-real-ip");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        log.info("IP信息, CF-Connecting-IP:{}, cf-connecting-ip:{}, x-real-ip:{}, x-forwarded-for:{}, Proxy-Client-IP:{}, WL-Proxy-Client-IP:{}, RemoteAddr:{}", new Object[]{request.getHeader("CF-Connecting-IP"), request.getHeader("cf-connecting-ip"), request.getHeader("x-real-ip"), request.getHeader("x-forwarded-for"), request.getHeader("Proxy-Client-IP"), request.getHeader("WL-Proxy-Client-IP"), request.getRemoteAddr()});
        return ip;
    }

    public static String getOutsideIP(HttpServletRequest request) {
        String allIP = getAllIP(request);
        return allIP.split(",")[0];
    }
}
