package com.cn.singleton;

/**
 * @ClassName Mag02
 * 懒汉式，什么时候用的时候才初始化
 * @Author zhanghongjun
 * @Date 2020-06-04 22:58
 * @Version 1.0
 */
public class Mag03 {
    private static Mag03 INSTANCE;


    /**
     * 私有从构造方法，别人没法new对象
     */
    private Mag03() {
    }

    public static Mag03 getInstance() {

        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mag03();
        }
        return INSTANCE;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //启动多个线程取到每次获取到对象的哈希值
            new Thread(() -> {
                System.out.println(Mag03.getInstance().hashCode());
            } ).start();

        }


    }
}
