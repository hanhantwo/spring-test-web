package com.cn.cor;

import com.cn.tank.*;

/**
 * @ClassName BulletWallCollider
 * @Description 子弹和墙相撞
 * @Author zhanghongjun
 * @Date 2020-06-09 0:03
 * @Version 1.0
 */
public class BulletWallCollider implements Collider{


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall){
            Bullet b = (Bullet)o1;
            Wall w = (Wall)o2;
            if(b.rect.intersects(w.rect)){
                b.die();
            }
        }else  if(o1 instanceof Wall  && o2 instanceof Bullet){
            return collide(o2,o1);
        }
        return true;
    }
}
