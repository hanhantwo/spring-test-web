package com.cn.tankFactory;

import com.cn.tank.Dir;
import com.cn.tank.Group;
import com.cn.tank.TankFrame;

/**
 * @ClassName RectFactory
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-07 13:55
 * @Version 1.0
 */
public class RectFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new RectTank(x,y,dir,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y,Dir dir, TankFrame tf,Group group) {
        return new RectBullet(x,y,dir,tf,group);
    }
}
