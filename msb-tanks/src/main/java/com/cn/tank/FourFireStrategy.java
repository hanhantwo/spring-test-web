package com.cn.tank;

/**
 * @ClassName FourFireStrategy
 * @Description 四个方向打子弹
 * @Author zhanghongjun
 * @Date 2020-06-06 0:13
 * @Version 1.0
 */
public class FourFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.y + Tank.HEIGTH / 2 - Bullet.HEIGTH / 2;
        Dir[] dir = Dir.values();
        for(Dir dir1:dir){
        new Bullet(bx, by, dir1, tank.fs, tank.group);
        }
        if(tank.group==Group.GOOD){
            new Thread(()->{
                new Audio("audio/tank_fire.wav");
            }).start();
        }
    }
}
