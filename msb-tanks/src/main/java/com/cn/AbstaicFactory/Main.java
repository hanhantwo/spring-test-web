package com.cn.AbstaicFactory;



/**
 * @ClassName Main
 * @Description 工厂模式
 * @Author zhanghongjun
 * @Date 2020-06-06 22:18
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
//        Car car = new Car();
//        car.go();
//        AK47 ak47 = new AK47();
//        ak47.shoot();
//        Bread b = new Bread();
//        b.reds();



        AbstaticFactory f = new MagicStick();
        Vehice v = f.createVehice();
        v.go();

    }
}
