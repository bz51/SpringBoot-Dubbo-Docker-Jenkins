package com.gaoxi.redis;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.redis.service.RedisServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GaoxiRedisApplicationTests {
	@Autowired
	private RedisServiceImpl redisUtil;

	@Test
	public void contextLoads() throws InterruptedException {
		UserEntity userEntity = new UserEntity();
		userEntity.setId("1");
		userEntity.setPassword("2");
		userEntity.setUsername("3");

		redisUtil.set("user",userEntity,1L);

		System.out.println("立即取："+redisUtil.get("user"));
		Thread.sleep(2000);
		System.out.println("2S后再取："+redisUtil.get("user"));

	}

}
