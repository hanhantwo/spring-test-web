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

    int x = 200, y = 200;

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
        graphics.fillRect(x, y, 50, 50);
//        x += 10;
//        y += 10;
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
        }
    }

}
