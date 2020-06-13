package com.cn.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建窗口类,继承Frame类，重写paint方法
 */
public class TankFrame extends Frame {
    GameModel gm =GameModel.getInstance();
    static final int GAME_WIDTH = 960, GAME_HEIGTH = 700;

    public TankFrame() {
        //设置窗口大小
        setSize(GAME_WIDTH, GAME_HEIGTH);
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

    /**
     * 解决闪烁问题 ，先画在内存中，图片大小和游戏画面一致，然后将内存的内容一次性画到屏幕上
     */
    Image image = null;
    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = this.createImage(GAME_WIDTH, GAME_HEIGTH);
        }
        Graphics graphics = image.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGTH);
        graphics.setColor(c);
        paint(graphics);
        g.drawImage(image, 0, 0, null);
    }


    @Override
    public void paint(Graphics graphics) {
        gm.paint( graphics);
    }

    /**
     * 内部类，处理键盘用的
     */
    class MykeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        /**
         * 键盘按下出发事件
         */
        @Override
        public void keyPressed(KeyEvent e) {
            //获取键盘值
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
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
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    gm.getMainTank().fire();
                    break;
                case KeyEvent.VK_S:
                    gm.save();
                    break;
                case KeyEvent.VK_L:
                    gm.load();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
          Tank  myTank = gm.getMainTank();
            if (!bL && !bU && !bR && !bD) {
                myTank.setMoveing(false);
            } else {
                myTank.setMoveing(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }

    }


}
