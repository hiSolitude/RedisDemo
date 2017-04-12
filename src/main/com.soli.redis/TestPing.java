package com.soli.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by solitude on 2017/3/31.
 *
 * 连接本机linux数据库，测试是否ping通
 */
public class TestPing {
    public static void main(String[] args){

        Jedis jedis = new Jedis("192.168.99.136",6379);

        System.out.println("成功连接redis数据库"+jedis.ping());
    }
}
