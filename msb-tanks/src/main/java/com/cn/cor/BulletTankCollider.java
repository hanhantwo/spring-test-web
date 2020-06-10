package com.cn.cor;

import com.cn.tank.*;

/**
 * @ClassName BulletTankCollider
 * @Description tank和子弹相撞
 * @Author zhanghongjun
 * @Date 2020-06-09 0:03
 * @Version 1.0
 */
public class BulletTankCollider implements Collider {


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            if (b.group == t.getGroup()) {
                return true;
            }
            /**
             * 判断两个方块是否相交
             */
            if (b.rect.intersects(t.rect)) {
                int ex = t.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int ey = t.getY() + Tank.HEIGTH / 2 - Explode.HEIGHT / 2;
                GameModel.getInstance().add(new Explode(ex, ey));
                t.die();
                b.die();
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;

    }
}
