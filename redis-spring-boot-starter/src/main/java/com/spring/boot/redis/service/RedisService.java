package com.spring.boot.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis service 核心类
 * 该服务封装了对redisTemplate的操作
 * 通过隐藏redisTemplate复杂操作，对外提供简单接口
 *
 * @author wangb
 * @create 2019/4/30
 * @since 1.0.0
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis值：String类型
     * get方法
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * redis值：String类型
     * set方法
     *
     * @param key        redis键
     * @param value      值
     * @param expireTime 过期时间
     * @param timeUnit   过期时间单位
     */
    public boolean set(final String key, Object value, long expireTime, TimeUnit timeUnit) {
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

    /**
     * 判断缓存中是否有对应的key
     *
     * @param key redis键
     * @return true-在，false-不在
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * redis值：hash类型
     * get方法
     *
     * @param key     redis键
     * @param hashKey hash表键
     * @return 值
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }


    /**
     * redis值：hash类型
     * set方法
     *
     * @param key     redis键
     * @param hashKey hash表键
     * @param value   要设置的值
     * @return true-成功，false-失败
     */
    public boolean hmSet(String key, Object hashKey, Object value) {
        boolean result = false;
        try {
            HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
            hash.put(key, hashKey, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * redis值：list类型
     * 添加数据
     *
     * @param k redis键
     * @param v 添加到列表的值
     */
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * redis值：list类型
     * 获取数据
     *
     * @param key   redis键
     * @param start 取值起始位置
     * @param end   取值结束位置
     */
    public List<Object> lRange(String key, long start, long end) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(key, start, end);
    }

    /**
     * redis值：set类型
     * 获取set所有数据
     *
     * @param key redis键
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * redis值：set类型
     * 添加数据
     *
     * @param key   redis键
     * @param value 需要添加的数据
     */
    public void sAdd(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * redis值：zset类型
     * 通过分数返回有序集合指定区间内的成员
     *
     * @param key      redis键
     * @param minScore
     * @param maxScore
     */
    public Set<Object> zRangeByScore(String key, double minScore, double maxScore) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, minScore, maxScore);
    }

    /**
     * redis值：zset类型
     * 添加
     *
     * @param key   redis键
     * @param value 值
     * @param score 分数（排序用）
     */
    public void zAdd(String key, Object value, double score) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, score);
    }


}
