package com.soli.spring_redis.dao.impl;

import com.soli.spring_redis.dao.AbstractBaseRedisDao;
import com.soli.spring_redis.dao.IUserDao;
import com.soli.spring_redis.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;

/**
 * Created by solitude on 2017/4/15.
 */
public class UserDao extends AbstractBaseRedisDao<String,User> implements IUserDao{
    /**
     * 新增
     *<br>------------------------------<br>
     * @param user
     * @return
     */
    public Object add(final User user) {
       Object o = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection redisConnection)
                throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize(user.getId());
                byte[] name = serializer.serialize(user.getName());
                return redisConnection.setNX(key, name);
            }
        });
       return o;
    }

    public Object add(final List<User> list) {
        Object o = redisTemplate.execute(new RedisCallback() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                for (User user :list){
                    byte[] key = serializer.serialize(user.getId());
                    byte[] name = serializer.serialize(user.getName());
                    redisConnection.setNX(key,name);
                }
                return true;
            }
        },false,true);
        return o;
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }

    public Object update(final User user) {
        String key = user.getId();
        if (get(key) == null) {
            throw new NullPointerException("数据行不存在, key = " + key);
        }
        Object result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key  = serializer.serialize(user.getId());
                byte[] name = serializer.serialize(user.getName());
                connection.set(key, name);
                return true;
            }
        });
        return result;
    }

    public User get(final String keyId) {
        User result = (User) redisTemplate.execute(new RedisCallback<User>() {
            public User doInRedis(RedisConnection connection)
                throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(keyId);
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                String name = serializer.deserialize(value);
                return new User(keyId, name, null);
            }
        });
        return result;
    }
}
