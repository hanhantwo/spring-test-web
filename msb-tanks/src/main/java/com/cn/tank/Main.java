package com.cn.tank;


public class Main {
    public static void main(String[] args) throws Exception {
        //new一个窗口类
        TankFrame frame = new TankFrame();
        /**
         * 初始化敌方坦克
         */
         Object o =   PropertyMgr.get("tanksCount");
        for (int i = 0; i < Integer.parseInt(o.toString()); i++) {
            frame.tanks.add(frame.gf.createTank(50+i*100,200,Dir.DOWN, frame,Group.BAD));
        }
        while (true) {
            Thread.sleep(50);
         frame.repaint();
        }
    }

}





