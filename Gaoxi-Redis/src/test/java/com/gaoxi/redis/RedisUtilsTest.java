package com.gaoxi.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilsTest {

    @Autowired
    private RedisUtilsImpl redisUtilsImpl;

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
        redisUtilsImpl.set("name","chai");
        System.out.println(redisUtilsImpl.get("name").toString());
        System.out.println();
    }

    @Test
    public void set1() throws Exception {
    }

}