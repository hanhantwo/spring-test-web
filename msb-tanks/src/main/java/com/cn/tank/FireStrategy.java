package com.cn.tank;

import com.cn.tankFactory.BaseTank;

/**
 * 定义开火策略
 */
public interface FireStrategy {
    void fire(Tank tank);
}
