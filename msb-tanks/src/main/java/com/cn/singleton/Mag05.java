package com.cn.singleton;

/**
 * @ClassName Mag02
 * synchronized 上锁
 * @Author zhanghongjun
 * @Date 2020-06-04 22:58
 * @Version 1.0
 */
public class Mag05 {
    private static Mag05 INSTANCE;


    /**
     * 私有从构造方法，别人没法new对象
     */
    private Mag05() {
    }
    /**
     * synchronized 上锁，因为锁在方法上加上用static修饰的，给类对象上锁，效率很低，因此就优化成锁放到方法内部中
     **/
    public static  Mag05 getInstance() {

        if (INSTANCE == null) {
            /**
            * 妄图通过减小同步代码块的方式，但是这种没有启动线程安全的问题
             * 多个线程同时进入到判断里面，虽然只有一个线程进入synchronized中，但是当你出来后另外一个线程又进去new了一个对象
             */
            synchronized (Mag05.class) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Mag05();
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //启动多个线程取到每次获取到对象的哈希值
            new Thread(() -> {
                System.out.println(Mag05.getInstance().hashCode());
            } ).start();

        }


    }
}
