package com.hb.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 缓存接口
 */
public interface ICacheService {

    /**
     * 日志操作对象
     */
    static final Logger LOGGER = LoggerFactory.getLogger(ICacheService.class);

    /**
     * 删除对应key的value
     *
     * @param key 键
     * @return Boolean
     */
    Boolean remove(String key);

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key 键
     * @return Boolean
     */
    Boolean exists(String key);

    /**
     * 放入缓存
     *  @param key   键
     * @param value 值
     */
    void put(String key, Object value);

    /**
     * 从缓存中取出
     *
     * @param key       键
     * @param className 值的类型的类名
     * @return Object
     */
    Object get(String key, String className);

    /**
     * 放入缓存成功后打印
     *
     * @param key 键
     */
    default void printPutCacheSuccess(Object key) {
        LOGGER.info("put cache of key[{}] success!", key);
    }

    /**
     * 取出缓存成功后打印
     *
     * @param key 键
     */
    default void printGetCacheSuccess(Object key) {
        LOGGER.info("get cache of key[{}] success!", key);
    }

}
