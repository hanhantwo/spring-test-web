package com.cn.cor;

import com.cn.tank.Bullet;
import com.cn.tank.GameObject;
import com.cn.tank.Tank;

/**
 * @ClassName BulletTankCollider
 * @Description tank和tank相撞
 * @Author zhanghongjun
 * @Date 2020-06-09 0:03
 * @Version 1.0
 */
public class TankTankCollider implements Collider {


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())) {
                t1.back();
                t2.back();
            }
        }
        return true;

    }
}
