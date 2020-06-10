package com.cn.cor;

import com.cn.tank.Bullet;
import com.cn.tank.GameObject;
import com.cn.tank.Tank;
import com.cn.tank.Wall;

/**
 * @ClassName TankWallCollider
 * @Description tank和墙相撞
 * @Author zhanghongjun
 * @Date 2020-06-09 0:03
 * @Version 1.0
 */
public class TankWallCollider implements Collider {


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank t = (Tank)o1;
            Wall w = (Wall)o2;
            if(t.rect.intersects(w.rect)){
               t.back();
            }
        }else  if(o1 instanceof Wall  && o2 instanceof Tank){
            return collide(o2,o1);
        }
        return true;
    }
}
