package com.mashibing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName MainAop
 * @Description spring AOP实现原理
 * @Author zhanghongjun
 * @Date 2020-06-12 23:50
 * @Version 1.0
 */
public class MainAop {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        Tank tank = (Tank)context.getBean("tank");
        tank.move();
    }
}
