import com.soli.spring_redis.dao.AbstractBaseRedisDao;
import com.soli.spring_redis.dao.IUserDao;
import com.soli.spring_redis.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by solitude on 2017/4/15.
 *
 * 测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class RedisTest extends AbstractBaseRedisDao{
    @Autowired
    private IUserDao userDao;

    /**
     * 新增
     * <br>------------------------------<br>
     */
    @Test
    public void testAddUser(){
        User user =  new User();
        user.setId("111");
        user.setName("张三");
        Object o = userDao.add(user);
    }

    @Test
    public void testAddUsers1() {
        List<User> list = new ArrayList<User>();
        for (int i = 10; i < 50000; i++) {
            User user = new User();
            user.setId("user" + i);
            user.setName("java2000_wl" + i);
            list.add(user);
        }
        long begin = System.currentTimeMillis();
        for (User user : list) {
            userDao.add(user);
        }
        System.out.println(System.currentTimeMillis() -  begin);
    }

    /**
     * 批量新增 pipeline方式
     * <br>------------------------------<br>
     */
    @Test
    public void testAddUsers2() {
        List<User> list = new ArrayList<User>();
        for (int i = 10; i < 1500000; i++) {
            User user = new User();
            user.setId("user" + i);
            user.setName("java2000_wl" + i);
            list.add(user);
        }
        long begin = System.currentTimeMillis();
        Object result = userDao.add(list);
        System.out.println(System.currentTimeMillis() - begin);
    }
    /**
     * 修改
     * <br>------------------------------<br>
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId("user11");
        user.setName("new_password");
        Object result = userDao.update(user);
        Assert.assertNotNull(result);
    }
    /**
     * 通过key删除单个
     * <br>------------------------------<br>
     */
    @Test
    public void testDelete() {
        String key = "user11";
        userDao.delete(key);
    }
    /**
     * 批量删除
     * <br>------------------------------<br>
     */
    @Test
    public void testDeletes() {
        List<String> list = new ArrayList<String>();
        for (int i = 10; i < 100; i++) {
            list.add("user" + i);
        }
        userDao.delete(list);
    }
    /**
     * 获取
     * <br>------------------------------<br>
     */
    @Test
    public void testGetUser() {
        String id = "user11";
        User user = userDao.get(id);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getName(), "java2000_l11");
    }
}
