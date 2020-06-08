package com.cn.tank;

import java.awt.*;

/**
 * @ClassName GameObject
 * @Description 游戏物体的父类
 * @Author zhanghongjun
 * @Date 2020-06-08 23:34
 * @Version 1.0
 */
public abstract class GameObject {

    int x,y;
    public abstract void paint(Graphics g);

}
