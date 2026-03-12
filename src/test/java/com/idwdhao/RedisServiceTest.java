package com.idwdhao;

import com.idwdhao.service.impl.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    void testSetAndGet() {
        String key = "test:spring:key";
        String value = "Hello Spring Data Redis";

        // 写入
        redisService.set(key, value);
        //redisTemplate.opsForValue().set(key, value);

        // 读取
        String retrieved = redisService.get(key);
/*
        // 断言
        assertThat(retrieved).isEqualTo(value);
        System.out.println("测试通过，获取到值: " + retrieved);

        // 清理
        redisService.delete(key);

 */
    }

    @Test
    void testExpire() throws InterruptedException {
        String key = "test:expire";
        String value = "temp";

        // 写入并设置 2 秒过期
        redisService.setWithExpire(key, value, 2);

        // 立即获取，应该存在
        assertThat(redisService.get(key)).isEqualTo(value);

        // 等待 3 秒
        Thread.sleep(3000);

        // 再次获取，应为 null
        assertThat(redisService.get(key)).isNull();
        System.out.println("过期测试通过");
    }
}