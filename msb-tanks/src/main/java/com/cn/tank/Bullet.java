package com.cn.tank;

import java.awt.*;

/**
 * @ClassName Bullet
 * @Description 子弹类
 * @Author luo15251835249
 * @Date 2020/5/31 23:53
 */
public class Bullet {
    private static final int SPEED = 5;
    private int x, y;
    private Dir dir;
    public static int WIDTH = ResourcesMgr.bulletD.getWidth(), HEIGTH = ResourcesMgr.bulletD.getHeight();
    private TankFrame fs = null;
    private boolean live = true;

    private Group group = Group.BAD;


    public Bullet(int x, int y, Dir dir, TankFrame fs, Group group) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.fs = fs;
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!live) {
            fs.bullets.remove(this);
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGTH) {
            live = false;
        }
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }
        //用一个Rectangle类记录子弹数量
        Rectangle rectangle1 = new Rectangle(this.x, this.y, WIDTH, HEIGTH);
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGTH);
        /**
         * 判断两个方块是否相交
         */
        if (rectangle1.intersects(rectangle2)) {
            Explode ex =  new Explode(tank.getX(),tank.getY(),tank.getFs());
            fs.explodes.add(ex);
            tank.die();
            this.die();
            ex.die();
        }
    }

    public void die() {
        this.live = false;
    }
}
