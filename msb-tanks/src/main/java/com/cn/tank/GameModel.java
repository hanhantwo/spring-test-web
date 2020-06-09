package com.cn.tank;

import com.cn.cor.BulletTankCollider;
import com.cn.cor.Collider;
import com.cn.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GameModel
 * @Description 门面模式
 * @Author zhanghongjun
 * @Date 2020-06-08 22:47
 * @Version 1.0
 */
public class GameModel {

    Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.GOOD);
//    List<Tank> tanks = new ArrayList<>();
//    List<Bullet> bullets = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();
    //运用策略模式和责任链模式，筛选条件是责任链模式，不同的类型的碰撞就是实现接口就是策略模式
    Collider collider = new BulletTankCollider();
    Collider collider2 = new TankTankCollider();

    List<GameObject> objects = new ArrayList<>();

    public void getTank() {
        /**
         * 初始化敌方坦克
         */
        Object o = PropertyMgr.get("tanksCount");

        for (int i = 0; i < Integer.parseInt(o.toString()); i++) {
            add(new Tank(50 + i * 100, 200, Dir.DOWN, this, Group.BAD));
        }

    }

    public GameModel() {

    }

    public void add(GameObject object) {
        this.objects.add(object);
    }

    public void remove(GameObject object) {
        this.objects.remove(object);
    }

    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
//        graphics.drawString("子弹数量为：" + bullets.size(), 10, 60);
//        graphics.drawString("敌军坦克数量为：" + tanks.size(), 10, 80);
//        graphics.drawString("爆炸数量为：" + explodes.size(), 10, 100);
        graphics.setColor(c);

        myTank.paint(graphics);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(graphics);
        }

        //碰撞检测
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 =objects.get(j);
                collider.collide(o1,o2);
                collider2.collide(o1,o2);
            }
        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
