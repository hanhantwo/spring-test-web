package com.cn.thread;

import java.util.Map;

/**
 * @ClassName MyRnunable
 * @Description TODO
 * @Author
 * @Date 2021/3/2 13:44
 */
public class MyRnunable implements Runnable {
    private Map<Integer,String> map;

    public MyRnunable(Map<Integer, String> map) {
        this.map = map;
    }
    @Override
    public void run() {
        System.out.println( "线程名称："+Thread.currentThread().getName());
        map.forEach((k,v)->{
            System.out.println("获取到的key:"+k+"  value:"+v);
        });
    }
}
