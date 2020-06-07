package com.cn.tankFactory;

import com.cn.tank.Tank;

import java.awt.*;

/**
 * @ClassName BaseBullet
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-07 13:27
 * @Version 1.0
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);
    public abstract  void collideWith(BaseTank tank);
}
