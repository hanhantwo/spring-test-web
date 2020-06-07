package com.cn.tankFactory;

import com.cn.tank.Dir;
import com.cn.tank.Group;
import com.cn.tank.TankFrame;

import java.awt.*;

/**
 * @ClassName BaseTank
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-07 13:24
 * @Version 1.0
 */
public abstract class BaseTank {
    public  Group getGroup(){
        return  Group.BAD;
    }
    public abstract void paint(Graphics graphics);
    public Rectangle rect = new Rectangle();
    public abstract void die();
    public abstract int getX();
    public abstract int getY();
    public abstract TankFrame getFs();
}
