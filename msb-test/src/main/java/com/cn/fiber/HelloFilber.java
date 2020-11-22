package com.cn.fiber;


public class HelloFilber {

    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                salc();
            }
        };

        int size = 10000;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread();

        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    static void salc() {
        int result = 0;
        for (int m = 0; m < 10000; m++) {
            for (int i = 0; i < 200; i++) {
                result += i;
            }
        }
    }
}
