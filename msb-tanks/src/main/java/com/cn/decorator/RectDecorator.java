package com.cn.decorator;

import com.cn.tank.GameObject;

import java.awt.*;

/**
 * @ClassName RectDecorator
 * @Description 方块装饰
 * @Author zhanghongjun
 * @Date 2020-06-11 23:21
 * @Version 1.0
 */
public class RectDecorator extends GODdecorator {

    public RectDecorator(GameObject go) {
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
        g.drawRect(this.x , this.y , super.go.getWidth(), super.go.getHeigth());
        g.setColor(c);
    }

}
