package com.cn.redis;/*
													* @Description:
													* @Author: zhanghongjun
													* @Date: 2021/7/2
													**/

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TestRedis {
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	@Qualifier("srt")
	StringRedisTemplate stringRedisTemplate;

	public void testRedis() {
		Jackson2HashMapper jackson2HashMapper = new Jackson2HashMapper(objectMapper, false);
		Person p = new Person();
		p.setName("张军");
		p.setAge(16);
		stringRedisTemplate.opsForHash().putAll("sean01", jackson2HashMapper.toHash(p));
		Map sean01 = stringRedisTemplate.opsForHash().entries("sean01");
		Person person = objectMapper.convertValue(sean01, Person.class);
		System.err.println(person.toString());
	}
}
