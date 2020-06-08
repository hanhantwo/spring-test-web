package com.cn.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @ClassName Bullet
 * @Description 子弹类
 * @Author luo15251835249
 * @Date 2020/5/31 23:53
 */
public class Bullet extends GameObject implements Comparable<Integer>{
    private static final int SPEED = 5;
    private int x, y;
    private Dir dir;
    public static int WIDTH = ResourcesMgr.bulletD.getWidth(), HEIGTH = ResourcesMgr.bulletD.getHeight();
    private GameModel gm = null;
    private boolean live = true;

    private Group group = Group.BAD;

    Rectangle rect = new Rectangle();
    public Bullet(int x, int y, Dir dir, GameModel gm, Group group) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        rect.x = x;
        rect.y= y;
        rect.width=WIDTH;
        rect.height=HEIGTH;
        gm.add(this);
    }

    public void paint(Graphics g) {
        if (!live) {
            gm.remove(this);
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
        rect.y= this.y;
        rect.width=this.WIDTH;
        rect.height=this.HEIGTH;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGTH) {
            live = false;
        }
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }
        //用一个Rectangle类记录子弹数量,new太多对象不好
        Rectangle rectangle1 = new Rectangle(this.x, this.y, WIDTH, HEIGTH);
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGTH);
        /**
         * 判断两个方块是否相交
         */
        if (rect.intersects(tank.rect)) {
            gm.add(new Explode(tank.getX(),tank.getY(),tank.gm));
            tank.die();
            this.die();
        }
    }

    public void die() {
        this.live = false;
    }

    @Override
    public int compareTo(Integer i) {
        if(i== KeyEvent.VK_ALT){
            return 1;
        }else if(i== KeyEvent.VK_SPACE){
            return 2;
        }else{
            return 0;
        }
    }
}
