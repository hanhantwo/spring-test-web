package com.cn.thread;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @ClassName MyCallable
 * @Description TODO
 * @Author
 * @Date 2021/3/2 11:35
 */
public class MyCallable implements Callable {
    private Map<Integer,String> map;

    public MyCallable(Map<Integer, String> map) {
        this.map = map;
    }

    @Override
    public Object call() throws Exception {

        String str = "线程名称："+Thread.currentThread().getName();
        map.forEach((k,v)->{
            System.out.println("获取到的key:"+k+"  value:"+v);
        });

        return str;
    }
}
