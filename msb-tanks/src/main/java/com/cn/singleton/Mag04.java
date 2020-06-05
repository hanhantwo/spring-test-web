package com.cn.singleton;

/**
 * @ClassName Mag02
 * synchronized 上锁
 * @Author zhanghongjun
 * @Date 2020-06-04 22:58
 * @Version 1.0
 */
public class Mag04 {
    private static Mag04 INSTANCE;


    /**
     * 私有从构造方法，别人没法new对象
     */
    private Mag04() {
    }
    //synchronized 上锁，因为锁在方法上加上用static修饰的，给类对象上锁，效率很低
    public static synchronized Mag04 getInstance() {

        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mag04();
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
                System.out.println(Mag04.getInstance().hashCode());
            } ).start();

        }


    }
}
