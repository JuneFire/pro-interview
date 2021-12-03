package redis;

/**
 * @author zkCheng
 * @date 2021/11/30 16:08
 */


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {

    private static final int MAX_ACTIVE = 20;

    private static final int MAX_IDLE = 5;

    private static final int MAX_WAIT = 1000;

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 6379;

    private static JedisPool jedisPool;

    private RedisClient() {
    }

    /**
     * 初始化非切片池
     */
    private static void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWait(MAX_WAIT);
        jedisPool = new JedisPool(config, HOST, PORT);
    }


    public static JedisPool getJedis() {
        if (jedisPool == null) {
            synchronized (RedisClient.class) {
                if (jedisPool == null) {
                    initialPool();
                }
            }
        }
        return jedisPool;
    }

}
