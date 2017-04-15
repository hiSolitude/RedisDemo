package com.soli.spring_redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Created by solitude on 2017/4/15.
 */
public abstract class AbstractBaseRedisDao<k,v> {

    @Autowired
    protected RedisTemplate redisTemplate;

    /**
     * 获取 RedisSerializer
     * <br>------------------------------<br>
     */
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 设置redisTemplate
     * @param redisTemplate the redisTemplate to set
     */
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * 获取 RedisSerializer
     * <br>------------------------------<br>
     */
    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }
}
