package com.cn.tankFactory;

import com.cn.tank.*;

import java.awt.*;

/**
 * @ClassName RectBullet
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-07 14:14
 * @Version 1.0
 */
public class RectBullet extends BaseBullet {
    private static final int SPEED = 5;
    private int x, y;
    private Dir dir;
    public static int WIDTH = ResourcesMgr.bulletD.getWidth(), HEIGTH = ResourcesMgr.bulletD.getHeight();
    private TankFrame fs = null;
    private boolean live = true;

    private Group group = Group.BAD;

    Rectangle rect = new Rectangle();

    public RectBullet(int x, int y, Dir dir, TankFrame fs, Group group) {

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.fs = fs;
        this.group = group;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGTH;
        fs.bullets.add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (!live) {
            fs.bullets.remove(this);
        }
       Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,20,20);
        g.setColor(c);
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

    @Override
    public void collideWith(BaseTank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }
        //用一个Rectangle类记录子弹数量,new太多对象不好
//        Rectangle rectangle1 = new Rectangle(this.x, this.y, WIDTH, HEIGTH);
//        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), tank.WIDTH, tank.HEIGTH);
        /**
         * 判断两个方块是否相交
         */
        if (rect.intersects(tank.rect)) {
            fs.explodes.add(fs.gf.createExplode(tank.getX(), tank.getY(), tank.getFs()));
            tank.die();
            this.die();
        }
    }

    public void die() {
        this.live = false;
    }

}

