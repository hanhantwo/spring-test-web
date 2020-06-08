package com.cn.tank;

/**
 * @ClassName DefaltFireStrategy
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-05 23:57
 * @Version 1.0
 */
public class DefaltFireStrategy implements FireStrategy {
//    static final DefaltFireStrategy INSTANCE = new DefaltFireStrategy();
//
//    private DefaltFireStrategy(){
//
//    }
//
//    public static DefaltFireStrategy getInstance(){
//        return INSTANCE;
//    }

    @Override
    public void fire(Tank tank) {
        int bx = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.y + Tank.HEIGTH / 2 - Bullet.HEIGTH / 2;
        new Bullet(bx, by, tank.dir, tank.gm, tank.group);
        if(tank.group==Group.GOOD){
            new Thread(()->{
                new Audio("tank_fire.wav");
            }).start();
        }
    }
}
