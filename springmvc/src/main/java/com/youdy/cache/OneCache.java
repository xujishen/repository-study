package com.youdy.cache;

import redis.clients.jedis.Jedis;

import java.io.Serializable;

public class OneCache implements Serializable {
	
	private static final long serialVersionUID = 2023093286899452771L;

	private static Jedis cache;
	
	// 无参构造方法
	public OneCache() {
		if (cache == null) {
			cache = new Jedis("10.128.8.135", 6379);
			//cache.auth("requirepass");
			
			/**
			 * (1). Redis中的字符串是基于SDS(Simple Dynamic String)存储的.
			 * (2). Sadd是基于
			 * (3). lpush是基于双端列表存储数据
			 * (4). hset是基于哈希字典存储数据
			 * (5). zadd是基于跳跃表
			 *
			 * ziplist压缩列表, intset是内存映射数据结构
			 * 其中intset只能升级不能降级是有序不重复
			 */
			cache.set("", "");  // 设置key Value
			cache.sadd("", "", "");
			cache.lpush("", "", "");
			cache.zadd("", 1, "");
			cache.hset("", "", "");
		}
	}
	
	@SuppressWarnings("unused")
	public static Jedis getCache() {
		if (cache == null) {
			/*JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(10);
			config.setMaxWaitMillis(1000 * 10);
			config.setMinIdle(5);
			JedisPool pool = new JedisPool(config, "10.128.7.111", 6379, 100000);
			cache = pool.getResource();*/
			cache = new Jedis("10.128.8.135", 6379);
			//cache.auth("xujishen");
		}
		return cache;
	}
	
	/**
	 * 添加set
	 * @param key
	 * @param strs
	 */
	public Long sadd(String key, String ... strs) {
		return cache.sadd(key, strs);
	}
	
	/**
	 * 添加某个 key
	 * @param key
	 * @param t
	 * @param nxxx
	 * @param expx
	 * @param time
	 */
	public String set(String key, byte[] t, String nxxx, String expx, long time) {
		return cache.set(key.getBytes(), t/*, nxxx, expx, time*/);
	}
	
	public byte[] get(String key) {
		return cache.get(key.getBytes());
	}
	
	/**
     * 检查key是否已经存在
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return cache.exists(key);
    }
    /**
     * 清空redis 所有数据
     * @return
     */
    public String flushDB() {
        return cache.flushDB();
    }
    /**
     * 查看redis里有多少数据
     */
    public long dbSize() {
        return cache.dbSize();
    }
    /**
     * 检查是否连接成功
     * @return
     */
    public String ping() {
        return cache.ping();
    }

    public void close() {
		cache.close();
	}
	
}
