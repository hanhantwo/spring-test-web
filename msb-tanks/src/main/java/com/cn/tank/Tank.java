package com.cn.tank;

import lombok.Data;

import java.awt.*;
import java.util.Random;

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
    private static final int SPEED = 3;
    private boolean moveing = true;
    private boolean live = true;
    public static int WIDTH = ResourcesMgr.tankD.getWidth(), HEIGTH = ResourcesMgr.tankD.getHeight();

    private TankFrame fs = null;
    private Group  group = Group.BAD;

    private Random random= new Random();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TankFrame getFs() {
        return fs;
    }

    public Dir getDir() {
        return dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public Group getGroup() {
        return group;
    }
    public Tank(int x, int y, Dir dir, TankFrame fs,Group  group) {
        super();
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.fs = fs;
        this.group = group;
    }

    public void paint(Graphics graphics) {
        if(!live){
            fs.tanks.remove(this);
        }
        /**
         * 根据方向赋值对应的图片
         */
        switch (dir) {
            case LEFT:
                graphics.drawImage(ResourcesMgr.tankL, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourcesMgr.tankU, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourcesMgr.tankR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourcesMgr.tankD, x, y, null);
                break;
        }
        move();
    }

    public void setMoveing(boolean moveing) {
        this.moveing = moveing;
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

        if(random.nextInt(10)>8){
            this.fire();
        }
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y+ Tank.HEIGTH / 2 - Bullet.HEIGTH / 2;
        //开火的时候添加子弹
        fs.bullets.add(new Bullet(bx, by, this.dir, fs,this.group));
    }
    public void die() {
        this.live=false;

    }

}
