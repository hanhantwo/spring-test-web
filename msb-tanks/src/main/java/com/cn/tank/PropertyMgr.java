package com.cn.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName PropertyMgr
 * @Description 通过类加载的方式读取配置文件信息
 * @Author zhanghongjun
 * @Date 2020-06-03 23:22
 * @Version 1.0
 */
public class PropertyMgr {
    static Properties properties = new Properties();
    static {

        try {
            /**
             * 通过类加载的方式读取配置文件信息，但是用的是static,在启动的时候就
             * 将所有的配置文件就拿进来，这样设计肯定不好，最好是用的时候再去拿
             */
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("application.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Object get(String key){
        if(properties==null){
            return null;
        }
        return properties.get(key);

    }

}
