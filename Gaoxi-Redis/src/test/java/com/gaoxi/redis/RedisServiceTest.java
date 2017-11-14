package com.gaoxi.redis;

import com.gaoxi.redis.service.RedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisServiceImpl redisServiceImpl;

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void removePattern() throws Exception {
    }

    @Test
    public void remove1() throws Exception {
    }

    @Test
    public void exists() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void set() throws Exception {
        redisServiceImpl.set("name","chai");
        System.out.println(redisServiceImpl.get("name").toString());
        System.out.println();
    }

    @Test
    public void set1() throws Exception {
    }

}