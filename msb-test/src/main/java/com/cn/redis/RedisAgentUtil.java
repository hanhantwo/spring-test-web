package com.cn.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RedisUtil 发布与订阅功能 - 发信息
 * @Description TODO
 * @Author
 * @Date 2021/7/13 22:14
 */
@Configuration
@Slf4j
public class RedisAgentUtil {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	/**
	 * redis发布与订阅 生产者
	 * 
	 * @param channel 通道
	 * @param msg     信息
	 */
	public void sendMsgTwo(String channel, String msg) {
		stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		stringRedisTemplate.convertAndSend(channel, msg);
	}

	/**
	 * 请求日志记录
	 * 
	 * @param channel
	 * @param msg
	 */
	public void sendMsgLog(String channel, String msg) {
		stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		stringRedisTemplate.convertAndSend(channel, msg);
	}
}
