package com.cn.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @ClassName GameObject
 * @Description 游戏物体的父类
 * @Author zhanghongjun
 * @Date 2020-06-08 23:34
 * @Version 1.0
 */
public abstract class GameObject implements Serializable{

    public int x,y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeigth();

}
