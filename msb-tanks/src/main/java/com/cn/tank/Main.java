package com.cn.tank;


public class Main {
    public static void main(String[] args) throws Exception {
        //new一个窗口类
        TankFrame frame = new TankFrame();
        while (true) {
            Thread.sleep(50);
         frame.repaint();
        }
    }

}





