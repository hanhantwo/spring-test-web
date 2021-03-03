package com.cn.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.lang.Nullable;

import javax.annotation.Resource;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * @Author
 * @Date 2021/3/3 10:14
 */
@Configuration
@Slf4j
public class RedisUtil {
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    public boolean setMsg(String key, Object value) {

        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            log.error("向redis插入数据失败：{}", e);
            return false;
        }

        return true;
    }

    /**
     * redis发布与订阅
     * 生产者
     * @param channel  通道
     * @param msg  信息
     */
    public void sendMsgTwo(String channel, String msg) {
        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        stringRedisTemplate.convertAndSend(channel, msg);
    }

    /**
     * 根据通道消费信息
     *
     */
    public void subScribe(){
        //获取连接
      RedisConnection rc = stringRedisTemplate.getConnectionFactory().getConnection();
         //该方法两个参数，第二个是指定通道名称
        rc.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, @Nullable byte[] bytes) {
                //消息转换
                byte[] bytes1 = message.getBody();
                String msg = new String(bytes1);
                log.info("获取到信息是："+msg);
            }
        },"channel".getBytes());
    }


}
