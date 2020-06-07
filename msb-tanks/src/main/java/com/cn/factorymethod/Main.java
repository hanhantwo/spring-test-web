package com.cn.factorymethod;

/**
 * @ClassName Main
 * @Description 工厂模式
 * @Author zhanghongjun
 * @Date 2020-06-06 22:18
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Move mv = new CarFactory().createCar();
        mv.go();
    }
}
