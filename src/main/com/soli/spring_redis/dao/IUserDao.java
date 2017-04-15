package com.soli.spring_redis.dao;

import com.soli.spring_redis.entity.User;

import java.util.List;

/**
 * Created by solitude on 2017/4/15.
 */
public interface IUserDao {
    /**
     * 新增
     * <br>------------------------------<br>
     * @param user
     * @return
     */
    Object add(User user);

    /**
     * 批量新增 使用pipeline方式
     * <br>------------------------------<br>
     * @param list
     * @return
     */
    Object add(List<User> list);

    /**
     * 删除
     * <br>------------------------------<br>
     * @param key
     */
    void delete(String key);

    /**
     * 删除多个
     * <br>------------------------------<br>
     * @param keys
     */
    void delete(List<String> keys);

    /**
     * 修改
     * <br>------------------------------<br>
     * @param user
     * @return
     */
    Object update(User user);

    /**
     * 通过key获取
     * <br>------------------------------<br>
     * @param keyId
     * @return
     */
    User get(String keyId);
}
