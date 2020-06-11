package com.cn.decorator;

import com.cn.tank.GameObject;

import java.awt.*;

/**
 * @ClassName RectDecorator
 * @Description 尾巴装饰
 * @Author zhanghongjun
 * @Date 2020-06-11 23:21
 * @Version 1.0
 */
public class TailDecorator extends GODdecorator {

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeigth() {
        return super.go.getHeigth();
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
        this.x =super.go.x;
        this.y =super.go.y;
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(this.x, this.y , super.go.x+super.go.getWidth(), super.go.y+super.go.getHeigth());
        g.setColor(c);
    }

}
