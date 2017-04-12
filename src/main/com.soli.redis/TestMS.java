package com.soli.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by solitude on 2017/4/12.
 *
 * 主从复制
 * 需要两个reids.conf，一个端口为6379，一个为6380
 */
public class TestMS {
    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("127.0.0.1",6379);
        Jedis jedis_S = new Jedis("127.0.0.1",6380);

        jedis_S.slaveof("127.0.0.1",6379);

        jedis_M.set("class","1122V2");

        String result = jedis_S.get("class");
        System.out.println(result);
    }
}
