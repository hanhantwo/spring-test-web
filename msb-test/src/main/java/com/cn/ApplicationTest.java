package com.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationTest {
	public static void main(String[] args) {
		ConfigurableApplicationContext ca = SpringApplication.run(ApplicationTest.class);
		// 测试redis序列化的问题
		TestRedis testRedis = ca.getBean(TestRedis.class);
		testRedis.testRedis();
	}
}
