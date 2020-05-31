package com.cn.tank;

import lombok.Data;
import lombok.Setter;

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

    public void setMoveing(boolean moveing) {
        this.moveing = moveing;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir) {
        super();
        this.dir = dir;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics graphics) {
        graphics.fillRect(x, y, 50, 50);
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

}
