package com.cn.tankFactory;

import com.cn.tank.Dir;
import com.cn.tank.Group;
import com.cn.tank.TankFrame;

/**
 * @ClassName GameFactory
 * @Description 游戏抽象工厂
 * @Author zhanghongjun
 * @Date 2020-06-07 13:21
 * @Version 1.0
 */
public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);

    public abstract BaseBullet createBullet(int x, int y,Dir dir,TankFrame tf,Group group);

}
