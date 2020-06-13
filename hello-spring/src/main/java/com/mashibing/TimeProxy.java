package com.mashibing;

/**
 * @ClassName TimeProxy
 * @Description 切入方法
 * @Author zhanghongjun
 * @Date 2020-06-12 23:56
 * @Version 1.0
 */
public class TimeProxy {

    public void before(){
        System.out.println("测试aop开始执行。。。。。");
    }
    public void after(){
        System.out.println("测试aop结束执行。。。。。");
    }
}
