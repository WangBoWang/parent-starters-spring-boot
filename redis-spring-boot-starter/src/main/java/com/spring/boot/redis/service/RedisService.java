package com.spring.boot.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * redis service 核心类
 * 该服务封装了对redisTemplate的操作
 * 通过隐藏redisTemplate复杂操作，对外提供简单接口
 * @author wangb
 * @create 2019/4/30
 * @since 1.0.0
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * value为String类型
     * get方法
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * value为String类型
     * set方法
     * @param key 键
     * @param value 值
     * @param expireTime 过去时间
     * @param timeUnit 过期时间单位
     */
    public boolean set(final String key,Object value, long expireTime,TimeUnit timeUnit) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
