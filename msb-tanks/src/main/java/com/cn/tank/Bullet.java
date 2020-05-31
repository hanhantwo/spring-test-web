package com.cn.tank;

import java.awt.*;

/**
 * @ClassName Bullet
 * @Description 子弹类
 * @Author luo15251835249
 * @Date 2020/5/31 23:53
 */
public class Bullet {
    private static final int SPEED =5;
    private int x,y;
    private Dir dir;
    private static int WIDTH=10,HEIGTH=10;


    public Bullet(int x,int y,Dir dir){

        this.x=x;
        this.y=y;
        this.dir=dir;
    }

    public  void paint(Graphics g){
        Color c =g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGTH);
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
    }


}
