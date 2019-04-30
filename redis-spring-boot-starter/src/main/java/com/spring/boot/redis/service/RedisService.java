package com.spring.boot.redis.service;

import com.spring.boot.redis.properties.RedisProperties;

/**
 * redis service 核心类
 * @author wangb
 * @create 2019/4/30
 * @since 1.0.0
 */
public class RedisService {
    private RedisProperties redisProperties;

    public RedisService(RedisProperties redisProperties){
        this.redisProperties = redisProperties;
    }
}
