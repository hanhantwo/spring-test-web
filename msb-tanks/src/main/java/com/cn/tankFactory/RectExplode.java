package com.cn.tankFactory;

import com.cn.tank.ResourcesMgr;
import com.cn.tank.TankFrame;
import lombok.Data;

import java.awt.*;

/**
 * @ClassName RectExplode
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-07 13:55
 * @Version 1.0
 */
@Data
public class RectExplode extends BaseExplode {
    private int x, y;
    private TankFrame fs = null;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame fs) {
        this.x = x;
        this.y = y;
        this.fs = fs;
    }
    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourcesMgr.explodes[step++], x, y, null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        step++;
        if (step >=5) {
            fs.explodes.remove(this);
        }
        g.setColor(c);
    }
}
