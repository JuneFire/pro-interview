package redis;

/**
 * @author zkCheng
 * @date 2021/11/30 16:14
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {

    public static void main(String[] args) {
        JedisPool jedisPool = RedisClient.getJedis();
        Jedis jedis = jedisPool.getResource();
        jedis.select(10);
        try {
            System.out.println("testString=================begin");
            testString(jedis);
            System.out.println("testString=================end");
            System.out.println("testList=================begin");
            testList(jedis);
            System.out.println("testList=================end");
            System.out.println("testSet=================begin");
            testSet(jedis);
            System.out.println("testSet=================end");
            System.out.println("testSortedSet=================begin");
            testSortedSet(jedis);
            System.out.println("testSortedSet=================end");
            System.out.println("testHash=================begin");
            testHash(jedis);
            System.out.println("testHash=================end");

            String key = "main.java.com.test";
            jedis.set(key,"key");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            jedisPool.returnResource(jedis);
        }
    }

    private static void testString(Jedis jedis) {
        String key = "main.java.com.test:string:a";
        // 为了保持多次测试结果一样，每次开始时都删除
        jedis.del(key);
        String value = "hello redis";
        jedis.set(key, value);
        String val = jedis.get(key);
        System.out.println(val);
        // 结果：hello redis
    }

    private static void testList(Jedis jedis) {
        String key = "main.java.com.test:list:a";
        // 为了保持多次测试结果一样，每次开始时都删除
        jedis.del(key);
        List<String> values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            char c = (char) (65 + i);
            values.add(String.valueOf(c));
        }
        for (int i = 0; i < values.size(); i++) {
            jedis.lpush(key, values.get(i));
        }
        List<String> val = jedis.lrange(key, 0, -1);
        System.out.println(val);
        // 结果：[J, I, H, G, F, E, D, C, B, A]
    }

    private static void testSet(Jedis jedis) {
        String key = "main.java.com.test:set:a";
        // 为了保持多次测试结果一样，每次开始时都删除
        jedis.del(key);
        Set<String> values = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            char c = (char) (65 + i);
            values.add(String.valueOf(c));
        }
        Iterator<String> ite = values.iterator();
        while (ite.hasNext()) {
            String value = ite.next();
            jedis.sadd(key, value);
        }
        Set<String> val = jedis.smembers(key);
        System.out.println(val);
        // 结果：[D, E, F, G, A, B, C, H, I, J]
    }

    private static void testSortedSet(Jedis jedis) {
        String key = "main.java.com.test:sortedset:a";
        // 为了保持多次测试结果一样，每次开始时都删除
        jedis.del(key);
        String[] values = { "C", "B", "G", "D", "d" };
        jedis.zadd(key, 10, "E");
        for (int i = 0; i < values.length; i++) {
            jedis.zadd(key, i + 10, values[i]);
        }
        jedis.zadd(key, 10, "F");
        Set<String> val = jedis.zrange(key, 0, -1);
        System.out.println(val);
        // 结果：[C, E, F, B, G, D, d]
    }

    private static void testHash(Jedis jedis) {
        String key = "main.java.com.test:hash:a";
        // 为了保持多次测试结果一样，每次开始时都删除
        jedis.del(key);
        Map<String, String> values = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String s = String.valueOf((char) (i + 'A'));
            values.put(s, s + "_val");
        }
        Iterator<Entry<String, String>> ite = values.entrySet().iterator();
        while (ite.hasNext()) {
            Entry<String, String> entry = ite.next();
            String k = entry.getKey();
            String v = entry.getValue();
            jedis.hset(key, k, v);
        }
        Map<String, String> val = jedis.hgetAll(key);
        System.out.println(val);
        // 结果：{D=D_val, E=E_val, F=F_val, G=G_val, A=A_val, B=B_val, C=C_val,
        // H=H_val, I=I_val, J=J_val}
    }

}
