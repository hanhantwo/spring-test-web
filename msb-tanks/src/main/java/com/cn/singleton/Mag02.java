package com.cn.singleton;

/**
 * @ClassName Mag02
 * 和Mag03一样的 只是用静态代码块实例化
 * @Author zhanghongjun
 * @Date 2020-06-04 22:58
 * @Version 1.0
 */
public class Mag02 {
    private static final Mag02 INSTANCE;

    static {
        INSTANCE = new Mag02();
    }

    /**
     * 私有从构造方法，别人没法new对象
     */
    private Mag02() {
    }

    public static Mag02 getInstance() {
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mag02 mag01 = Mag02.getInstance();
        Mag02 mag011 = Mag02.getInstance();
        System.out.println(mag01 == mag011);
    }
}
