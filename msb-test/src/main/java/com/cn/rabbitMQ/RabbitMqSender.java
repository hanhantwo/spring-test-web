package com.cn.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName RabbitMqSender
 * @Description TODO
 * @Author
 * @Date 2021/3/2 15:10
 */
@Slf4j
public class RabbitMqSender {
    @Autowired
    RabbitTemplate rabbitTemplate;

    //交换机
    @Value("${cucumber.exchange}")
    private String exchange;

    @Value("${cucumber.routing}")
    private String routingKey;
    //队列名称
    @Value("${cucumber.queue}")
    private String queueName;

    /**
     * yml配置文件
     *   cucumber：
     *     exchange: testlink.exchange.dev
     *     queue：testlink.queue.dev
     *     routing:testlink.routing.dev
     *
     * 发送信息
     * 向指定交换机和路由发信息，就能到对于的队列中，就可以直接监听队列获取信息了
     * @param msg
     * @return
     */
    public int senderMsg(String msg){
        try {
            //设置信息格式
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(exchange);
            rabbitTemplate.setExchange(routingKey);

            rabbitTemplate.convertAndSend(msg);
        } catch (AmqpException e) {
            log.error("发送信息失败{}",e);
            return -1;
        }
        return 1;
    }


    /**
     * yml配置文件
     *   cucumber：
     *     exchange: testlink.exchange.dev
     *     queue：testlink.queue.dev
     *     routing:testlink.routing.dev
     *
     *获取上面发送的信息
     * 监听mq队列获取信息（监听信息又两种写法，这是一种，还有一种就是MqReceiver这个类方法）
     */
    //案例监听testlink队列，该队列有交换机和路由
    @RabbitListener(queues = "${cucumber.queue}",containerFactory = "singleListenerContainer")
    public void listenerStringMsg(String msg){
         log.info("打印获取的信息："+msg);

    }

    /**
     * 监听mq队列获取信息（监听信息又两种写法，这是一种，还有一种就是MqReceiver这个类方法）
     */
    @RabbitListener(queues = "${cucumber.queue}",containerFactory = "singleListenerContainer")
    public void listenerByteMsg(byte[] bytes){
        try {
            log.info("打印获取的信息："+new String(bytes,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("信息转换报错：{}",e);
        }

    }

}
