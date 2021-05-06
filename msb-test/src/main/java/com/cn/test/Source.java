package com.cn.test;

public class Source {
    public void method1() {
        System.out.println("This is original method...");
    }
}
interface Targetable {

    /**
     * 与原类中的方法相同
     */
     void method1();

    /**
     * 新类的方法
     */
     void method2();
}
class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("This is the targetable method...");
    }
}

 class AdapterPattern {
    public static void main(String[] args) {
        Targetable targetable = new Adapter();
        targetable.method1();
        targetable.method2();
    }

}

