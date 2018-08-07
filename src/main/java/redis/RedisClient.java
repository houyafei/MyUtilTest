package redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * redis使用测试
 *
 * @author yafei.hou  on 2018/7/27
 */
public class RedisClient {

    // 非切片客户端链接对象
    private Jedis jedis;

    // 非切片链接池对象
    private JedisPool jedisPool;

    // 切片客户端链接对象
    private ShardedJedis shardedJedis;

    // 切片链接池
    private ShardedJedisPool shardedJedisPool;

    // IP以及端口
    private String ip = "172.17.245.33";
    private int port = 7000;

    // 构造函数,完成客户端连接的工作
    public RedisClient() {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();
    }

    private void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, ip, port);
    }

    // 初始化切片池
    private void initialShardedPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo(ip, 7000, "master"));
        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    // 测试主类
    public static void main(String[] args) {
        new RedisClient().show();
    }

    public void show(){
//        testKey();
        testExpirt();
//        System.out.println(jedis.get("expire_test"));
    }

    private void testKey() {
        System.out.println("=============Test key==========================");
        // 清空数据
        System.out.println(jedis.flushDB());
        System.out.println(jedis.echo("foo"));
        // 判断key否存在
        System.out.println(shardedJedis.exists("foo"));
        shardedJedis.set("key", "values");
        System.out.println(shardedJedis.exists("key"));
    }

    private void testExpirt(){
        System.out.println("=============Test expire==========================");
        String key = "expire_test";
        boolean flag = true;
        if (!jedis.exists(key)){
            jedis.set(key,"today is a much better day");
            jedis.expire(key,100);
//            jedis.expireAt(key,System.currentTimeMillis()/1000+10);
        }
        while (flag){
            System.out.println(jedis.get(key));
            System.out.println("剩余时间："+jedis.ttl(key));
            try {
                Thread.sleep(1700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (jedis.ttl(key)<0){
                flag = false;
            }

        }

    }

}
