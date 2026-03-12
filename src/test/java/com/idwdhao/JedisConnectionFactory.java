package com.idwdhao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class JedisConnectionFactory {

    // Redis服务器信息（建议从配置文件读取）
    private static final String HOST = "192.168.58.130";
    private static final int PORT = 6379;
    private static final String PASSWORD = "111111";
    private static final int TIMEOUT = Protocol.DEFAULT_TIMEOUT; // 2000ms

    // 连接池配置参数
    private static final int MAX_TOTAL = 20;
    private static final int MAX_IDLE = 10;
    private static final int MIN_IDLE = 5;
    private static final long MAX_WAIT_MILLIS = 3000L;
    private static final boolean TEST_ON_BORROW = true;
    private static final boolean TEST_ON_RETURN = false;
    private static final boolean TEST_WHILE_IDLE = true;

    // 连接池对象（static final，在静态代码块中初始化）
    private static final JedisPool jedisPool;

    // 私有构造器，防止实例化
    private JedisConnectionFactory() {}

    // 静态代码块：初始化连接池
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_TOTAL);
        config.setMaxIdle(MAX_IDLE);
        config.setMinIdle(MIN_IDLE);
        config.setMaxWaitMillis(MAX_WAIT_MILLIS);
        config.setTestOnBorrow(TEST_ON_BORROW);
        config.setTestOnReturn(TEST_ON_RETURN);
        config.setTestWhileIdle(TEST_WHILE_IDLE);
        config.setBlockWhenExhausted(true);

        jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT, PASSWORD);
    }

    /**
     * 获取Jedis连接
     * @return Jedis实例（使用后必须调用close()归还连接，建议try-with-resources）
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 关闭连接池（应用关闭时调用）
     */
    public static void closePool() {
        if (jedisPool != null && !jedisPool.isClosed()) {
            jedisPool.close();
        }
    }

}
