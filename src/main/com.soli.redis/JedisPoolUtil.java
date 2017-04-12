package com.soli.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by solitude on 2017/4/12.
 *
 * redis工具类
 */
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool;
    private JedisPoolUtil(){

    }

    public static JedisPool getJedisPoolInstance(){
        if (null == jedisPool){
            synchronized (JedisPoolUtil.class){
                if (null == jedisPool){
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxIdle(32);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig,"192.168.99.136",6379);
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis){
        if (null != jedis){
            jedisPool.returnResourceObject(jedis);
        }
    }

}
