package com.cn.tank;

import lombok.Data;

import java.awt.*;
import java.io.Serializable;

/**
 * @ClassName Explode
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-03 0:34
 * @Version 1.0
 */
@Data
public class Explode extends GameObject {
    public static int WIDTH = ResourcesMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourcesMgr.explodes[0].getHeight();
    private int step = 0;
    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourcesMgr.explodes[step++], x, y, null);
        if (step >= ResourcesMgr.explodes.length) {
            GameModel.getInstance().remove(this);
        }
    }
    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeigth() {
        return HEIGHT;
    }
}
