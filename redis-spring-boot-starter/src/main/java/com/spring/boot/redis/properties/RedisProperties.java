package com.spring.boot.redis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis 属性类
 * @author wangb
 * @create 2019/4/30
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String host;

    private String password;

    private String port;

}
