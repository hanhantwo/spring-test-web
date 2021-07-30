package com.cn.redis;/*
													* @Description:
													* @Author: zhanghongjun
													* @Date: 2021/7/2
													**/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class MyTemplate {
	@Bean
	public StringRedisTemplate srt(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate st = new StringRedisTemplate(redisConnectionFactory);
		st.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		return st;
	};

}
