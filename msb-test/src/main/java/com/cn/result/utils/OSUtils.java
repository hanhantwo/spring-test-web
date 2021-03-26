package com.cn.result.utils;

/**
 * @ClassName: OSUtils
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/3/26 11:39
 */
public class OSUtils {
    private static final String OS_NAME_PROPERTY = "os.name";

    public OSUtils() {
    }

    public static OSUtils.OS getOSType() {
        String osName = System.getProperty("os.name").toLowerCase();
        OSUtils.OS[] var1 = OSUtils.OS.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            OSUtils.OS os = var1[var3];
            if (osName.contains(os.toString())) {
                return os;
            }
        }

        return OSUtils.OS.other;
    }

    public static enum OS {
        windows,
        linux,
        sunos,
        solaris,
        mac,
        unix,
        other;

        private OS() {
        }
    }
}
