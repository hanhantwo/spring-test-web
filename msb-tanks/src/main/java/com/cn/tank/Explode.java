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
public class Explode extends GameObject{
    private int x, y;
    private GameModel gm = null;
    private int step = 0;
    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
//        new Audio("").run();
    }


    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourcesMgr.explodes[step++], x, y, null);
        if (step >= ResourcesMgr.explodes.length) {
            gm.remove(this);
        }
    }

}
