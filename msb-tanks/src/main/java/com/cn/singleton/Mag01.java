package com.cn.singleton;

/**
 * @ClassName Mag01
 * @Description
 *
 * 饿汉方式
 * 类加载到内存后，就是实例化一个单例，JVM保证线程安全
 *唯一的缺点不管用不用都会实例化
 *
 * @Author zhanghongjun
 * @Date 2020-06-04 22:58
 * @Version 1.0
 */
public class Mag01 {
    private static final Mag01 INSTANCE = new Mag01();

    /**
     * 私有从构造方法，别人没法new对象
     */
    private Mag01(){}
    public static Mag01 getInstance(){
        return INSTANCE;
    }
    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mag01 mag01 = Mag01.getInstance();
        Mag01 mag011 = Mag01.getInstance();
        System.out.println(mag01==mag011);
    }
}
