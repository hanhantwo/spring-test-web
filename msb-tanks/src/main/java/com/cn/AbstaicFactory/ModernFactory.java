package com.cn.AbstaicFactory;

/**
 * @ClassName ModernFactory
 * @Description 实际工厂
 * @Author zhanghongjun
 * @Date 2020-06-06 23:23
 * @Version 1.0
 */
public class ModernFactory extends AbstaticFactory {
    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Vehice createVehice() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new AK47();
    }
}
