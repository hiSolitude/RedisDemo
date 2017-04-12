package com.soli.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by solitude on 2017/4/12.
 */
public class TestPool {
    public static void main(String[] args){
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        JedisPool jedisPool1 = JedisPoolUtil.getJedisPoolInstance();

        System.out.println(jedisPool == jedisPool1);

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("aa","bb");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JedisPoolUtil.release(jedisPool,jedis);
        }
    }
}
