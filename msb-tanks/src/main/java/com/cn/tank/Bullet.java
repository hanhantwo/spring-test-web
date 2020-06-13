package com.cn.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * @ClassName Bullet
 * @Description 子弹类
 * @Author luo15251835249
 * @Date 2020/5/31 23:53
 */
public class Bullet extends GameObject  {
    private static final int SPEED = 5;
    private Dir dir;
    public static int WIDTH = ResourcesMgr.bulletD.getWidth(), HEIGTH = ResourcesMgr.bulletD.getHeight();
    private boolean live = true;

    public Group group = Group.BAD;

    public Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGTH;
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (!live) {
            GameModel.getInstance().remove(this);
        }
        /**
         * 根据方向赋值对应的图片
         */
        switch (dir) {
            case LEFT:
                g.drawImage(ResourcesMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourcesMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourcesMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourcesMgr.bulletD, x, y, null);
                break;
        }
        move();

    }

    public void move() {
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

        rect.x = this.x;
        rect.y = this.y;
        rect.width = this.WIDTH;
        rect.height = this.HEIGTH;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGTH) {
            live = false;
        }
    }

    public void die() {
        live = false;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeigth() {
        return HEIGTH;
    }
}
