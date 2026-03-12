package com.idwdhao;

import com.idwdhao.User;  // 根据你的实体类路径调整
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisSerializerTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;  // 注入你配置的 RedisTemplate

    @Test
    public void testObjectSerialization() {
        // 1. 创建一个对象
        User user = new User("李四", 30);

        // 2. 存入 Redis
        String key = "test:user:1002";
        redisTemplate.opsForValue().set(key, user);

        // 3. 从 Redis 读取
        User retrievedUser = (User) redisTemplate.opsForValue().get(key);

        // 4. 验证结果
        System.out.println("存入对象: " + user);
        System.out.println("读取对象: " + retrievedUser);

        // 使用断言确保读取的对象不为空且属性一致
        assert retrievedUser != null;
        assert retrievedUser.getName().equals(user.getName());
        assert retrievedUser.getAge().equals(user.getAge());

        // 5. 可选：清理测试数据
        //redisTemplate.delete(key);
    }
}