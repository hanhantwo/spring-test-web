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
            INSTANCE = new Mag03();
        }
        return  INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mag03 mag01 = Mag03.getInstance();
        Mag03 mag011 = Mag03.getInstance();
        System.out.println(mag01 == mag011);
    }
}
