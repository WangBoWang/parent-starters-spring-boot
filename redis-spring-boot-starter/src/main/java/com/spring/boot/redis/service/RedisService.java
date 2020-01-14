package com.spring.boot.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis service 核心类
 * @author wangb
 * @create 2019/4/30
 * @since 1.0.0
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


}
