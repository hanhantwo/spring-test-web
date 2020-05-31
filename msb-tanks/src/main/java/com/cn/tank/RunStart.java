package com.cn.tank;

public class RunStart {
    public static void main(String[] args) throws Exception{
        //new一个窗口类
        FrameStart frame = new FrameStart();
        while (true){
            Thread.sleep(50);
            frame.repaint();
        }
    }

}
