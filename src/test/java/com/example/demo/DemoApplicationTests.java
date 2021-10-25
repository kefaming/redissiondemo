package com.example.demo;

import com.example.demo.exception.BizException;
import com.example.demo.model.Serial;
import com.example.demo.model.User;
import com.example.demo.service.SerialService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private UserService userService;

    @Autowired
    private SerialService serialService;

    // 分布式ID
    @Test
    void distributedId() {

        int orderId = new Random().nextInt(1000);
        log.debug(String.valueOf(orderId));

        String key = "dec_store_lock_" + orderId;
        RLock lock = redissonClient.getLock(key);
        try {
            //加锁 操作很类似Java的ReentrantLock机制
            lock.lock(); // 也可用下面的写法
            // boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS); //尝试加锁，最多等待3秒，上锁以后10秒自动解锁
            // if(res){
            //     // do your business
            // }

            Serial serial = serialService.getSerialInfo("SEQ_USER_ID");

            Long userId = serialService.getNextValue("SEQ_USER_ID");
            System.out.println("=============== 分布式ID: " + userId + " ===============");

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            //解锁
            lock.unlock();
        }
    }

    // 分布式锁
//    @Test
    void distributedLock() {

        int orderId = new Random().nextInt(1000);
        log.debug(String.valueOf(orderId));

        String key = "dec_store_lock_" + orderId;
        RLock lock = redissonClient.getLock(key);
        try {
            //加锁 操作很类似Java的ReentrantLock机制
            lock.lock(); // 也可用下面的写法
            // boolean res = lock.tryLock(5,10, TimeUnit.SECONDS); //尝试加锁，最多等待3秒，上锁以后10秒自动解锁
            // if(res){
            //     // do your business
            // }

            // 查询库存
            List<User> list = userService.getUserInfo("test_mybatis_625", "pwd_mybatis_625");
            if(list != null && list.size() > 0){
                for (User user:list) {
                    System.out.println(user);
                }
            }

            // 如果库存为空或库存不足
            if (list.size() <= 0) {
               throw new BizException(1001, "库存不足");
            }

            // 扣减库存
            int i = orderId;
            User user = new User();
            user.setUserId(i);
            user.setUserName("test_mybatis_" + i);
            user.setPwd("pwd_mybatis_" + i);
            System.out.println(user);
            userService.insertUser(user);

            log.debug("=============== 执行业务操作完成 ===============");
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            //解锁
            lock.unlock();
        }
    }
}
