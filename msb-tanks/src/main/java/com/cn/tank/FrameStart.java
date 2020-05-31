package com.cn.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 创建窗口类,继承Frame类，重写paint方法
 */
public class FrameStart extends Frame {

    Tank myTank = new Tank(100,100,Dir.DOWN);
    Bullet bullet =new Bullet(300,300,Dir.DOWN);

    public FrameStart() {
        //设置窗口大小
        setSize(800, 600);
        //是否运行改动大小
        setResizable(false);
        //设置标题
        setTitle("tanks war");
        setVisible(true);
        //添加键盘监听
        this.addKeyListener(new MykeyListener());

        //增加一个windows窗口监听,点击× 关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics graphics) {

        myTank.paint(graphics);
        bullet.paint(graphics);
    }

    /**
     * 内部类，处理键盘用的
     */
    class MykeyListener extends KeyAdapter {
        boolean bL= false;
        boolean bU= false;
        boolean bR= false;
        boolean bD= false;
        /**
         * 键盘按下出发事件
         */
        @Override
        public void keyPressed(KeyEvent e) {
            //获取键盘值
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL=true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        /**
         * 键盘按钮提起来出发
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL=false;
                    break;
                case KeyEvent.VK_UP:
                    bU=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir(){
            if(!bL&&!bU&&!bR&&!bD){
                myTank.setMoveing(false);
            }
            else {
                myTank.setMoveing(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }

    }



}
