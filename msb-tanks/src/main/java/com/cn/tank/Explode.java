package com.cn.tank;

import lombok.Data;

import java.awt.*;

/**
 * @ClassName Explode
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-03 0:34
 * @Version 1.0
 */
@Data
public class Explode {
    private int x, y;
    public static int WIDTH = ResourcesMgr.explodes[0].getWidth();
    public static int HEIGTH = ResourcesMgr.explodes[0].getHeight();
    private TankFrame fs = null;
    private int step = 0;
    private boolean live = true;
    public Explode(int x, int y, TankFrame fs) {
        this.x = x;
        this.y = y;
        this.fs = fs;
//        new Audio("").run();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {

        g.drawImage(ResourcesMgr.explodes[step++], x, y, null);
        if (step >= ResourcesMgr.explodes.length) {
            step = 0;
        }
//        if(!live){
//            fs.explodes.remove(this);
//        }
    }
    public void die() {
        this.live = false;
    }

}
