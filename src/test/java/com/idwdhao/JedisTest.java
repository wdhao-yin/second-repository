package com.idwdhao;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JedisTest {

    private Jedis jedis;
    private final String host = "192.168.58.130";
    private final int port = 6379;
    private final String password = "111111";

    @BeforeEach  // JUnit 5 的 BeforeEach，替代 @Before
    public void setUp() {
        // 在每个测试方法前执行：建立连接并认证
        //jedis = new Jedis(host, port);
        jedis = JedisConnectionFactory.getJedis();
        jedis.auth(password);
        System.out.println("Connected to Redis: " + jedis.ping());
    }

    @AfterEach  // JUnit 5 的 AfterEach，替代 @After
    public void tearDown() {
        // 在每个测试方法后执行：关闭连接
        if (jedis != null) {
            jedis.close();
            System.out.println("Redis connection closed.");
        }
    }

    @Test  // JUnit 5 的 Test
    public void testStringOperations() {
        jedis.set("test:name", "张三");
        jedis.set("test:age", "25");

        String name = jedis.get("test:name");
        String age = jedis.get("test:age");

        assertEquals("张三", name);
        assertEquals("25", age);

        System.out.println("String test passed: name=" + name + ", age=" + age);
    }

    @Test
    public void testHashOperations() {
        jedis.hset("test:user:1001", "name", "李四");
        jedis.hset("test:user:1001", "age", "30");
        jedis.hset("test:user:1001", "city", "北京");

        Map<String, String> user = jedis.hgetAll("test:user:1001");
        assertNotNull(user);
        assertEquals("李四", user.get("name"));
        assertEquals("30", user.get("age"));
        assertEquals("北京", user.get("city"));

        System.out.println("Hash test passed: " + user);
    }

    @Test
    public void testListOperations() {
        jedis.lpush("test:messages", "Hello", "World", "Jedis");

        List<String> messages = jedis.lrange("test:messages", 0, -1);
        assertTrue(messages.contains("Hello"));
        assertTrue(messages.contains("World"));
        assertTrue(messages.contains("Jedis"));

        System.out.println("List test passed: " + messages);
    }

    @Test
    public void testSetOperations() {
        jedis.sadd("test:tags", "java", "redis", "jedis");

        List<String> tags = jedis.srandmember("test:tags", 3);
        assertNotNull(tags);
        assertTrue(tags.size() > 0);

        System.out.println("Set test passed: " + tags);
    }

}
