package com.cn.singleton;

/**
 * 不仅可以保证线程安全，还可以防止反序列化
 */
public enum Mag08 {
    INSTANCE;
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //启动多个线程取到每次获取到对象的哈希值
            new Thread(() -> {
                System.out.println(Mag08.INSTANCE.hashCode());
            }).start();

        }


    }
}
