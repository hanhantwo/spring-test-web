package com.cn.redis;
/*
													* @Description:
													* @Author: zhanghongjun
													* @Date: 2021/7/2
													**/

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
	private String name;
	private Integer age;

	@Override
	public String toString() {
		return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
	}
}
