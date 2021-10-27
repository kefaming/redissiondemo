package com.example.demo;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * 布隆过滤器
 */
@SpringBootTest
@Slf4j
public class BloomFilterTests {

    @Autowired
    private RedissonClient redisson;

    // 基于redisson实现布隆过滤器
//    @Test
    public void test() {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("phoneList");
        //初始化布隆过滤器：预计元素为100000000L,误差率为3%
        bloomFilter.tryInit(100000000L,0.03);
        //将号码10086插入到布隆过滤器中
        bloomFilter.add("10086");

        //判断下面号码是否在布隆过滤器中
        System.out.println(bloomFilter.contains("123456"));//false
        System.out.println(bloomFilter.contains("10086"));//true
    }

    // 基于Guava实现布隆过滤器
    @Test
    public void test2() {
        //创建字符串布隆过滤器，使用编码UTF-8
        //创建时需要传入四个参数，但我们只要关心前三个就行
        //Funnel，这是Guava中定义的一个接口，它和PrimitiveSink配套使用，主要是把任意类型的数据转化成Java基本数据类型（primitive value，如char，byte，int……），默认用java.nio.ByteBuffer实现，最终均转化为byte数组
        //expectedInsertions 期望插入数据数，int或long
        //fpp期望误判率，比如1E-7（千万分之一）
        //Strategy 策略，默认选取64位哈希映射函数，BloomFilterStrategies.MURMUR128_MITZ_64
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);

        //置入元素，其实也有boolean类型返回，但是尊重注解，就不返回值了
        bloomFilter.put("10086");

        //判断元素是否存在，true存在，false不存在。
        boolean isContain = bloomFilter.mightContain("10086");
        System.out.println(isContain);
        isContain = bloomFilter.mightContain("123456");
        System.out.println(isContain);

    }

}
