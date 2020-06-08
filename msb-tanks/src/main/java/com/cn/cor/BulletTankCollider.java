package com.cn.cor;

import com.cn.tank.Bullet;
import com.cn.tank.GameObject;
import com.cn.tank.Tank;

/**
 * @ClassName BulletTankCollider
 * @Description tank和子弹相撞
 * @Author zhanghongjun
 * @Date 2020-06-09 0:03
 * @Version 1.0
 */
public class BulletTankCollider implements Collider{


    @Override
    public void collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            b.collideWith(t);
        }else  if(o1 instanceof Tank  && o2 instanceof Bullet){
            collide(o2,o1);
        }else {
            return;
        }
    }
}
