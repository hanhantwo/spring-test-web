package com.cn.redis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/*
 * @Description:发布与订阅功能 - 订阅信息
 * @Author: zhanghongjun
 * @Date: 2021/7/19
 **/
@Configuration
@Slf4j
public class SubConfig {

	@Value("${channel.name}")
	private String channelName;
	@Value("${channel.recordLog}")
	private String channelRecordLogName;

	@Bean
	MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new Receiver(), "receiveMessage");
	}

	// 每多一个监听就创建一个bean
	@Bean
	MessageListenerAdapter messageListenerLog() {
		return new MessageListenerAdapter(new ReceiverLog(), "receiveMessage");
	}

	@Bean
	RedisMessageListenerContainer redisContainer(RedisConnectionFactory factory) {
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(factory);
		container.addMessageListener(this.messageListener(), new ChannelTopic(channelName));
		container.addMessageListener(this.messageListenerLog(), new ChannelTopic(channelRecordLogName));
		return container;
	}

	public class Receiver {
		public void receiveMessage(String message) {
			log.info("Received <" + message + ">");
			Map<String, String> map = JSONObject.parseObject(message, Map.class);
			log.info("收到信息map");
		}
	}

	public class ReceiverLog {
		public void receiveMessage(String message) {
			log.info("Received <" + message + ">");
			Map<String, String> map = JSONObject.parseObject(message, Map.class);
			log.info("收到信息map");
		}
	}

}
