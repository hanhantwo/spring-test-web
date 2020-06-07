package com.cn.factorymethod;

/**
 * @ClassName Plane
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-06 22:20
 * @Version 1.0
 */
public class Plane implements Move {
    @Override
    public void go(){
        System.out.println("开飞机。。。。");
    }
}
