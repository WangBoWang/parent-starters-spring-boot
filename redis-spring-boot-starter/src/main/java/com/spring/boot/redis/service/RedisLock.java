package com.spring.boot.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * redis分布式锁
 * 基于redis单线程
 * @author wangb
 * @version 1.0.0
 * @since 2020/1/4
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    private final static int EFAULT_RETRY_COUNT = 3;


    /**
     * 加锁
     * @param key 锁唯一标志（不是要缓存的数据的key）
     * @param expiredTimestamp 超时时间戳=System.currentTimeMillis()+超时时间；对应value。
     * 默认重试三次
     */
    public boolean lockRetry(String key, long expiredTimestamp){
        return lockRetry(key,expiredTimestamp,EFAULT_RETRY_COUNT);
    }


    /**
     * 加锁
     * @param key 锁唯一标志（不是要缓存的数据的key）
     * @param expiredTimestamp 超时时间戳=System.currentTimeMillis()+超时时间
     * @param retryCount redis锁获取失败重试次数
     */
    public boolean lockRetry(String key, long expiredTimestamp,int retryCount){
        if(retryCount<=0){
            //不合法时不重试
            retryCount = 1;
        }
        for(int i=0;i<retryCount;i++){
            if(lock(key,expiredTimestamp)){
                return true;
            }
        }
        return false;
    }


    /**
     * 加锁
     * @param key 锁唯一标志（不是要缓存的数据的key）
     * @param expiredTimestamp 超时时间戳=System.currentTimeMillis()+超时时间
     */
    public boolean lock(String key, long expiredTimestamp){
        String value = String.valueOf(expiredTimestamp);

        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }

        //判断锁超时,防止死锁
        String currentValue = (String)redisTemplate.opsForValue().get(key);
        //如果锁过期
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //获取上一个锁的时间value
            String oldValue = (String)redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                return true;
            }
        }
        return false;
    }
    /**
     * 解锁
     * @param key 加锁时用的key
     * @param value 加锁时设置的value
     */
    public void unlock(String key,String value){
        try {
            String currentValue =  (String) redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value) ){
                //删除key
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【Redis分布式锁】解锁出现异常了，{}",e);
        }
    }
}
