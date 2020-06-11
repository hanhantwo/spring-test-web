package com.cn.decorator;

import com.cn.tank.GameObject;

import java.awt.*;

/**
 * @ClassName GODdecorator
 * @Description 装饰模式类
 * @Author zhanghongjun
 * @Date 2020-06-11 23:17
 * @Version 1.0
 */
public abstract   class GODdecorator extends GameObject {
    GameObject go;

    public GODdecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }

}
