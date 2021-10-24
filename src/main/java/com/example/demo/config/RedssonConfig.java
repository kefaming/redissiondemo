package com.example.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedssonConfig {

    @Bean
    public RedissonClient getRedissionClient(){
        Config config = new Config();
//        config.useClusterServers()
//                // use "rediss://" for SSL connection
//                .addNodeAddress("redis://127.0.0.1:6379");

        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);

        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
