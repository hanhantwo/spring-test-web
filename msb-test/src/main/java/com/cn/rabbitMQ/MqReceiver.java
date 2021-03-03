package com.cn.rabbitMQ;

import lombok.CustomLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MqReceiver
 * @Description TODO 监听mq信息
 * @Author
 * @Date 2021/3/2 16:57
 */
@Slf4j
@Component
public class MqReceiver implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(message);
    }
}
