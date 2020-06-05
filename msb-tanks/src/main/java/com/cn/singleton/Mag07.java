package com.cn.singleton;

/**
 * @ClassName Mag02
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类的时候不会加载内部类，这样就可以实现懒加载了
 * @Author zhanghongjun
 * @Date 2020-06-04 22:58
 * @Version 1.0
 */
public class Mag07 {
    private static Mag07 INSTANCE;


    /**
     * 私有从构造方法，别人没法new对象
     */
    private Mag07() {
    }

    private static class Mag07Hloder {
        private static final Mag07 INSTANCE = new Mag07();
    }


    public static Mag07 getInstance() {
        return Mag07Hloder.INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //启动多个线程取到每次获取到对象的哈希值
            new Thread(() -> {
                System.out.println(Mag07.getInstance().hashCode());
            }).start();

        }


    }
}
