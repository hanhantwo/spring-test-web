package com.cn.tankFactory;

import com.cn.tank.*;

/**
 * @ClassName DefaultFactory
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-07 13:28
 * @Version 1.0
 */
public class DefaultFactory extends GameFactory {
    /**
     * 设置成单例的
     */
    private static final DefaultFactory INSTANCE = new DefaultFactory();
    private  DefaultFactory(){

    }

    public static DefaultFactory getInstance(){
        return INSTANCE;
    }
    @Override
    public  BaseTank createTank(int x, int y, Dir dir,TankFrame fs, Group group) {
        return new Tank(x,y,dir,fs,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode( x,  y,  tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y,Dir dir, TankFrame tf, Group group) {
        return new Bullet(x,y,dir,tf,group);
    }

}
