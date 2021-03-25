package com.cn.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Reflect
 * @Description: 通过反射调用方法
 * @Author: zhanghongjun
 * @Date: 2021/3/25 17:31
 */
public class Reflect {
    /**
     * 1.通过Class.forName(“包名+方法的类名”)拿到方法的对象;
     *
     * 如：Class<?> clazz = Class.forName("ReflectionTest");
     *
     *         也可以通过实例，获取方法的对象类，
     *
     *         如：String str = "test , reflection";
     *
     *         Class<?> clazz = str.getClass();
     *
     * 2.明确反射方法名称 ;
     *
     * 3.明确方法的参数类型，就可以拿到对象的方法。
     *
     * 如：Method method = clazz.getMethod("test",String.class,int.class)；
     */

    public static  void reflectMethod(){
        //todo:可用的反射调用方法
        try {
            Map<String, String> map = new HashMap<>();
            map.put("pageNum","1");
            map.put("pageSize","4");
            System.out.println(JSONObject.toJSONString(map));
            String remoteUrl = "http://127.0.0.1:8200/api/normElement/list";
            //todo:通过类路径拿取类
            Class<?> clazz = Class.forName("com.cn.util.HttpClient");
            /**
             *  Method method = clazz.getMethod("方法名","参数类型",参数类型);
             */
            Method method = clazz.getMethod("doGet",String.class,Map.class);
            /**
             * 实例化对象
             */
           String str= (String)method.invoke(clazz.newInstance(),remoteUrl,map);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        reflectMethod();
        System.out.println("os_name:" + System.getProperty( "os.name"));

        System.out.println("os_arch:" + System.getProperty( "os.arch"));

        System.out.println("os_version:" + System.getProperty( "os.version"));

        System.out.println("user_name:" + System.getProperty( "user.name"));

        System.out.println("user_home:" + System.getProperty( "user.home"));

        System.out.println("user_dir:" + System.getProperty( "user.dir"));
    }
}
