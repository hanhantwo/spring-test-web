package com.cn.tank;

public class Main {
    public static void main(String[] args) throws Exception {
        //new一个窗口类
        TankFrame frame = new TankFrame();

        /**
         * 初始化敌方坦克
         */
        for (int i = 0; i < 5; i++) {
            frame.tanks.add(new Tank(50+i*100,200,Dir.DOWN, frame,Group.BAD));
        }
        while (true) {
            Thread.sleep(50);
         frame.repaint();
        }
    }

}

//.



