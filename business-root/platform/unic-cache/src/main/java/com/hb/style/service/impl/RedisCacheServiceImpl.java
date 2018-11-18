package com.hb.style.service.impl;

import com.hb.style.service.AbstractRedisCacheService;
import com.hb.style.service.IRedisCacheService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by huangbiao on 2018/10/3.
 */
@Service("redisCacheService")
@Primary
public class RedisCacheServiceImpl extends AbstractRedisCacheService implements IRedisCacheService {

    @Override
    public void opsForValuePut(String key, Object value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (exists(key)) {
            remove(key);
        }
        valueOperations.set(key, value);
        printPutCacheSuccess(key);
    }

    @Override
    public Object opsForValueGet(String key) {
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
        Object o = boundValueOperations.get();
        printGetCacheSuccess(key);
        return o;
    }

    @Override
    public void opsForListPut(String key, List<Object> list) {
        ListOperations listOperations = redisTemplate.opsForList();
        if (exists(key)) {
            remove(key);
        }
        list.forEach(obj -> listOperations.rightPush(key, obj));
        printPutCacheSuccess(key);
    }

    @Override
    public List opsForListGet(String key) {
        BoundListOperations boundListOperations = redisTemplate.boundListOps(key);
        Long size = boundListOperations.size();
        List result = boundListOperations.range(0, size);
        printGetCacheSuccess(key);
        return result;
    }

    @Override
    public void opsForMapPut(String key, Map<String, Object> map) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (exists(key)) {
            remove(key);
        }
        map.entrySet().forEach(entry -> hashOperations.put(key, entry.getKey(), entry.getValue()));
        printPutCacheSuccess(key);
    }

    @Override
    public Map<String, Object> opsForMapGet(String key) {
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(key);
        Map map = boundHashOperations.entries();
        printGetCacheSuccess(key);
        return map;
    }

    @Override
    public void opsForSetPut(String key, Set<Object> set) {
        SetOperations setOperations = redisTemplate.opsForSet();
        if (exists(key)) {
            remove(key);
        }
        set.forEach(obj -> setOperations.add(key, obj));
        printPutCacheSuccess(key);
    }

    @Override
    public Set<Object> opsForSetGet(String key) {
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps(key);
        Set set = boundSetOperations.members();
        printGetCacheSuccess(key);
        return set;
    }

}
