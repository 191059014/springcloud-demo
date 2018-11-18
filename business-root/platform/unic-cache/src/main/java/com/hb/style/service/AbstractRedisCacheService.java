package com.hb.style.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 缓存抽象类
 */
public abstract class AbstractRedisCacheService implements IRedisCacheService, ICacheService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RedisTemplate redisTemplate;

    @Override
    public Boolean remove(final String key) {
        Boolean delete = redisTemplate.delete(key);
        logger.info("remove cache of key[{}] success!", key);
        return delete;
    }

    @Override
    public Boolean exists(final String key) {
        Boolean exist = redisTemplate.hasKey(key);
        logger.info("cache contains key[{}] : {}!", key, exist);
        return exist;
    }

    @Override
    public void put(String key, Object value) {
        if (value instanceof String) {
            opsForValuePut(key, value);
        } else if (value instanceof List) {
            opsForListPut(key, (List) value);
        } else if (value instanceof Map) {
            opsForMapPut(key, (Map) value);
        } else if (value instanceof Set) {
            opsForSetPut(key, (Set) value);
        }
    }

    @Override
    public Object get(String key, String className) {
        if ("String".equals(className)) {
            return opsForValueGet(key);
        } else if ("List".equals(className)) {
            return opsForListGet(key);
        } else if ("Map".equals(className)) {
            return opsForMapGet(key);
        } else if ("Set".equals(className)) {
            return opsForSetGet(key);
        }
        return null;
    }

}
