package com.cn.factorymethod;

/**
 * @ClassName CarFactory
 * @Description 针对Car 创建一个工厂
 * @Author zhanghongjun
 * @Date 2020-06-06 22:35
 * @Version 1.0
 */
public class CarFactory {
    public Move createCar() {
        System.out.println("a car create");
        return new Car();
    }
}
