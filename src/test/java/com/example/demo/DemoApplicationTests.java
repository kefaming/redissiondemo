package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.exception.BizException;
import com.example.demo.mapper.StorageMapper;
import com.example.demo.model.Storage;
import com.example.demo.service.SerialService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private SerialService serialService;

    @Resource
    private StorageMapper storageMapper;

    // 测试mybatis plus
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Storage> storageList = storageMapper.selectList(null);
        Assertions.assertEquals(1, storageList.size());
        storageList.forEach(System.out::println);

        Storage s = storageMapper.selectById("1");
        System.out.println(s);
    }


    // 分布式ID
//    @Test
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

            String commodityCode = "C201901140001"; // 商品ID
            int count = 10; // 购买数量

            // 查询库存
            QueryWrapper<Storage> qryWrapper = new QueryWrapper<>();
            qryWrapper.eq("commodity_code", commodityCode);
            Storage storage = storageMapper.selectOne(qryWrapper);

            // 如果库存不足
            if (storage.getCount() < count) {
                throw new BizException(1001, "库存不足");
            }

            // 扣减库存
            storageMapper.decreaseStorage(commodityCode, count);

            log.debug("=============== 扣减库存成功 ===============");
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            //解锁
            lock.unlock();
        }
    }
}
