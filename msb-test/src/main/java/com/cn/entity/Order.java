package com.cn.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * @Description:
 * @Author: zhanghongjun
 * @Date: 2021/8/18
 **/
@Setter
@Getter
@ToString
public class Order {
	/**
	 * 订单来源
	 */
	private String source;
	/**
	 * 支付方式
	 */
	private String payMethod;
	/**
	 * 订单编号
	 */
	private String code;
	/**
	 * 订单金额
	 */
	private int amount;
	// ...其他的一些字段
}
