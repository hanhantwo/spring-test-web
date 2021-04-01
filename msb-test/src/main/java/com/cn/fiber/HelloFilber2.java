package com.cn.fiber;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;


public class HelloFilber2 {

    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();

        int size = 10000;
        Fiber<Void>[] fibers = new Fiber[size];
        for (int i = 0; i < fibers.length; i++) {
            fibers[i] = new Fiber<Void>(new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    salc();
                }
            });
        }

        for (int i = 0; i < fibers.length; i++) {
            fibers[i].start();
        }
        for (int i = 0; i < fibers.length; i++) {
            fibers[i].join();
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
