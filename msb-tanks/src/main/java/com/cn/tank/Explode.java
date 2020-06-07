package com.cn.tank;

import com.cn.tankFactory.BaseExplode;
import lombok.Data;

import java.awt.*;

/**
 * @ClassName Explode
 * @Description 爆炸1
 * @Author zhanghongjun
 * @Date 2020-06-03 0:34
 * @Version 1.0
 */
@Data
public class Explode extends BaseExplode{
    private int x, y;
    private TankFrame fs = null;
    private int step = 0;
    public Explode(int x, int y, TankFrame fs) {
        this.x = x;
        this.y = y;
        this.fs = fs;
    }
   @Override
    public void paint(Graphics g) {
        g.drawImage(ResourcesMgr.explodes[step++], x, y, null);
        if (step >= ResourcesMgr.explodes.length) {
            fs.explodes.remove(this);
        }
    }

}
