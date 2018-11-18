package com.hb.cache.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRedisCacheService {

    /**
     * 对字符串的存储
     *
     * @param key   键
     * @param value 值
     */
    void opsForValuePut(String key, Object value);

    /**
     * 对字符串的获取
     *
     * @param key 键
     */
    Object opsForValueGet(String key);

    /**
     * 对List的存储
     *
     * @param key  键
     * @param list 值
     */
    void opsForListPut(String key, List<Object> list);

    /**
     * 对List的获取
     *
     * @param key 键
     */
    List opsForListGet(String key);

    /**
     * 对Map的存储
     *
     * @param key 键
     * @param map 值
     */
    void opsForMapPut(String key, Map<String, Object> map);

    /**
     * 对Map的获取
     *
     * @param key 键
     */
    Map<String, Object> opsForMapGet(String key);

    /**
     * 对Set的存储
     *
     * @param key 键
     * @param set 值
     */
    void opsForSetPut(String key, Set<Object> set);

    /**
     * 对Set的获取
     *
     * @param key 键
     */
    Set<Object> opsForSetGet(String key);
}
