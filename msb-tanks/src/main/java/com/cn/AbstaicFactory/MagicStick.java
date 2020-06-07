package com.cn.AbstaicFactory;

/**
 * @ClassName MagicStick
 * @Description 魔法一族
 * @Author zhanghongjun
 * @Date 2020-06-06 22:56
 * @Version 1.0
 */
public class MagicStick extends AbstaticFactory {
    @Override
    Food createFood() {
        return new Mybran();
    }

    @Override
    Vehice createVehice() {
        return new Broom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicSddg();
    }
}
