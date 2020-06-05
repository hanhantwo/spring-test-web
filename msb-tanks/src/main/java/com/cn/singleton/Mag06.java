package com.cn.singleton;

/**
 * @ClassName Mag02
 * synchronized 上锁
 * @Author zhanghongjun
 * @Date 2020-06-04 22:58
 * @Version 1.0
 */
public class Mag06 {
    private static Mag06 INSTANCE;


    /**
     * 私有从构造方法，别人没法new对象
     */
    private Mag06() {
    }

    /**
     * synchronized 上锁，因为锁在方法上加上用static修饰的，给类对象上锁，效率很低，因此就优化成锁放到方法内部中
     **/
    public static Mag06 getInstance() {

        if (INSTANCE == null) {
            /**
             * 双重检查机制，外面这个if肯定是有必有的，可以排除很多排队的，一旦对象不为null的时候就不在进来，减少竞争
             */
            synchronized (Mag06.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mag06();
                }
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
                System.out.println(Mag06.getInstance().hashCode());
            }).start();

        }


    }
}
