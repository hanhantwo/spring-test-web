package com.cn.tank;

import lombok.Data;
import java.awt.*;

/**
 * @ClassName Tank
 * @Description TODO
 * @Author
 * @Date 2020/5/31 23:04
 */
@Data
public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 10;
    private boolean moveing = false;

    private FrameStart fs=null;

    public Tank(int x, int y, Dir dir,FrameStart fs) {
        super();
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.fs=fs;
    }

    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(x, y, 50, 50);
        graphics.setColor(c);
        move();
    }

    public void move() {
        if (!moveing) {
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    public void fire(){
     fs.bullets.add(new Bullet(this.x, this.y, this.dir,fs));
    }
}
