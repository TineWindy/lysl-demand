package com.whu.lysl.service.cache;


/**
 * 缓存基础服务
 * @author christopher
 * @since 2019/10/12 12:54
 */
public interface CacheService {

    /**
     * 根据key获取cache值，不存在不会从数据库中取值更新
     * @param cacheKeyPrefix 缓存key前缀
     * @param key 缓存key
     * @param objClass 序列化的类，不需要时可传null
     * @return 缓存值
     */
    Object selectByKey(String cacheKeyPrefix, String key, Class<?> objClass);

    /**
     * 添加缓存
     * @param cacheKeyPrefix 缓存key前缀
     * @param key 缓存key
     * @param value 缓存value
     * @param expireTime 缓存时间，单位为ms，为0时使用系统默认缓存存在时间
     */
    void addByKey(String cacheKeyPrefix, String key, Object value, long expireTime);

    /**
     * 过期缓存
     * @param cacheKeyPrefix 缓存key前缀
     * @param key 缓存key
     * @param expireTime 过期时间，单位为ms;为0表示立即过期
     * @return 过期key数目
     */
    int expireKey(String cacheKeyPrefix, String key, long expireTime);

    /**
     * 批量过期缓存
     * @param cacheKeyPrefix 缓存前缀
     * @param expireTime 过期时间，单位为ms;为0表示立即过期
     * @return 过期key数目
     */
    int batchExpireKey(String cacheKeyPrefix, long expireTime);

}
