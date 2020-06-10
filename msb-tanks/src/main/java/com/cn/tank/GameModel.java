package com.cn.tank;

import com.cn.cor.BulletTankCollider;
import com.cn.cor.Collider;
import com.cn.cor.ColliderChain;
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
    private static final GameModel gm = new GameModel();

    static {
        gm.init();
    }

     Tank myTank = null;
    ColliderChain colliderChain = new ColliderChain();
    List<GameObject> objects = new ArrayList<>();

    private GameModel() {
    }

    private  void init() {
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);
        Object o = PropertyMgr.get("tanksCount");
        for (int i = 0; i < Integer.parseInt(o.toString()); i++) {
            new Tank(50 + i * 100, 200, Dir.DOWN, Group.BAD);
        }

        add(new Wall(120, 120, 200, 50));
        add(new Wall(220, 120, 200, 50));
        add(new Wall(300, 220, 50, 200));
        add(new Wall(550, 320, 50, 200));
    }

    public static GameModel getInstance() {
        return gm;
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
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                colliderChain.collide(o1, o2);
            }
        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
