package com.spring.boot.redis.autoconfigure;

import com.spring.boot.redis.properties.RedisProperties;
import com.spring.boot.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redis 配置类
 * @author wangb
 * @create 2019/4/30
 * @since 1.0.0
 */
@Configuration
@ConditionalOnClass(RedisService.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    @ConditionalOnMissingBean
    public RedisService  redisService(){
        return new RedisService(redisProperties);
    }

}
