package com.whu.lysl.service.cache.impl;

import com.whu.lysl.base.constants.CacheConstants;
import com.whu.lysl.base.utils.CacheUtils;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.service.cache.CacheService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 缓存实现
 *
 * @author christopher
 * @since 2019/10/12 21:02
 */
@Service
public class CacheServiceImpl implements CacheService {

    /**
     * redis 服务
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Object selectByKey(String cacheKeyPrefix, String key, Class<?> objClass) {
        // 取缓存值并鉴定
        String cacheValue = stringRedisTemplate.opsForValue().get(cacheKeyPrefix + key);
        return StringUtils.isNotEmpty(cacheValue) ? CacheUtils.string2Obj(cacheValue, objClass) : null;
    }

    @Override
    public void addByKey(String cacheKeyPrefix, String key, Object value, long expireTime) {
        if (!(StringUtils.isNotEmpty(cacheKeyPrefix) && StringUtils.isNotEmpty(key) && value != null)) {
            return;
        }
        // 序列化 value
        String valueStr;
        if (StringUtils.equal(value.getClass().getName(), String.class.getName())) {
            valueStr = (String) value;
        } else {
            valueStr = CacheUtils.obj2String(value);
        }

        // 放入redis缓存
        stringRedisTemplate.opsForValue().set(cacheKeyPrefix + key, valueStr);

        // 设置过期时间
        if (expireTime <= 0) {
            stringRedisTemplate.expire(cacheKeyPrefix + key, CacheConstants.CACHE_NORMAL_EXIST_TIME, TimeUnit.MILLISECONDS);
        } else {
            stringRedisTemplate.expire(cacheKeyPrefix + key, expireTime, TimeUnit.MILLISECONDS);
        }
    }


    @Override
    public int expireKey(String cacheKeyPrefix, String key, long expireTime) {
        try {
            if (stringRedisTemplate.expire(cacheKeyPrefix + key, expireTime, TimeUnit.MILLISECONDS)) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int batchExpireKey(String cacheKeyPrefix, long expireTime) {
        // todo
        return 0;
    }
}
