package com.cn.cor;

import com.cn.tank.GameObject;
import com.cn.tank.PropertyMgr;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ColliderChain
 * @Description 责任链封装
 * @Author zhanghongjun
 * @Date 2020-06-10 21:37
 * @Version 1.0
 */
public class ColliderChain implements Collider {
    /**
     * 用链表结构的集合，因为我不需要随机访问，
     * 某一个位置的数据，添加数据时只需要从尾巴上添加就是了，
     * 同时数组达到一定长度就会扩容，存在空间的浪费
     */
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new BulletWallCollider());
        add(new TankWallCollider());
    }

    public void add(Collider c) {
        colliders.add(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
           return !colliders.get(i).collide(o1, o2);
        }
        return true;
    }
}
