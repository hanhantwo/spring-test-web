package com.cn.service.impl;

import com.cn.entity.Order;
import com.cn.entity.enums.OrderHandlerType;
import com.cn.service.OrderHandler;

/*
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/8/18
 **/
@OrderHandlerType(source = "pc")
public class PCOrderHandler implements OrderHandler {
	@Override
	public void handle(Order order) {
		System.out.println("处理PC端订单");
	}
}
