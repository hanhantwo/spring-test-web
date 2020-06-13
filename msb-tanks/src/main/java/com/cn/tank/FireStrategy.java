package com.cn.tank;

import java.io.Serializable;

/**
 * 定义开火策略
 */
public interface FireStrategy  extends Serializable{
    void fire(Tank tank);
}
