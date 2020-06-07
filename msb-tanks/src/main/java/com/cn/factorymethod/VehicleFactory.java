package com.cn.factorymethod;

/**
 * @ClassName VehicleFactory
 * @Description 简单工厂的可扩展性不好
 * @Author zhanghongjun
 * @Date 2020-06-06 22:32
 * @Version 1.0
 */
public class VehicleFactory {

    public Car createCar() {
        return new Car();
    }

    public Plane createPlan() {
        return new Plane();
    }


}
