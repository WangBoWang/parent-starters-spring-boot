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

    /**
     * Redis服务器地址
     */
    private String host;

    /**
     * Redis服务器连接端口
     */
    private Integer port;

    /**
     * Redis服务器连接密码
     */
    private String password;

    /**
     * 连接池最大连接数（使用负值表示没有限制）
     */
    private Integer maxActive = 200;

    /**
     * 连接池最大阻塞等待时间（使用负值表示没有限制）
     */
    private Integer maxWait = -1;

    /**
     * 连接池中的最大空闲连接
     */
    private Integer maxIdle = 10;

    /**
     * 连接池中的最小空闲连接
     */
    private Integer minIdle = -1;

    /**
     * 连接超时时间（毫秒）
     */
    private Long timeOut = 1000L;

}
