package com.cn.rabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitMqConfig
 * @Description TODO mq单个队列监听配置
 * @Author
 * @Date 2021/3/2 16:48
 */
@Slf4j
@Configuration
public class RabbitMqConfig {
    @Autowired
    MqReceiver mqReceiver;

    @Value("${}")
    private String queueName;

    /**
     * 这个监听指定队列
     *
     * @param connectionFactory
     * @return
     */
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        //new 一个消息监听容器
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //关于ack讲解
        //blog.csdn.net/weixin_38380858/article/details/84963944
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        //设置监听的类
        container.setMessageListener(mqReceiver);
        //
        container.setQueueNames(queueName);
        return null;
    }

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer simpleRabbitListenerContainerFactoryConfigurer;

    /**
     * 注入singleListenerContainer监听容器
     *
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置并发消费者个数
        factory.setConcurrentConsumers(10);
        //设置最大并发消费者个数
        factory.setMaxConcurrentConsumers(20);

        factory.setPrefetchCount(1);

        factory.setTxSize(1);

        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);


        simpleRabbitListenerContainerFactoryConfigurer.configure(factory,cachingConnectionFactory);
        return factory;
    }

    /**
     * 追加RabbitTemplate书写
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(){
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                log.info("消息发送成功：{}",correlationData,b,s);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.info("消息丢失：{}",message,s1,s2);
            }
        });
        return rabbitTemplate;
    }


}
