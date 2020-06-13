package com.mashibing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Main
 * @Description springIOC原理重现
 * @Author zhanghongjun
 * @Date 2020-06-07 23:01
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
//        Driver driver = new Driver();

        ApplicationContext context = new ClassPathXmlApplicationContext("app.xml")   ;

//        Driver driver = (Driver)context.getBean("driver");
//        driver.getMsg();
        Tank tank = (Tank)context.getBean("tank");
//        tank.getDriver();
    }
}
