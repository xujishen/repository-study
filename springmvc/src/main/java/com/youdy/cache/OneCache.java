package com.youdy.cache;

import org.apache.commons.configuration.PropertiesConfiguration;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

public class OneCache implements Serializable {
	
	private static final long serialVersionUID = 2023093286899452771L;

	private static Jedis cache;
	private static String REDIS_PROPERTIES_FILE = "classpath:config/redis.properties";
	private static String host;
	private static int port;
	private static String auth;
	
	// the static statement
	static {
		initPropertis();
	}
	
	static void initPropertis() {
		try {
			PropertiesConfiguration pc = new PropertiesConfiguration(REDIS_PROPERTIES_FILE);
			host = (String) pc.getProperty("redis.host");
			port = new Integer((String) pc.getProperty("redis.port")).intValue();
			auth = (String) pc.getProperty("redis.auth");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 无参构造方法
	public OneCache() {
		if (cache == null) {
			cache = new Jedis(host, port);
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
			cache = new Jedis(host, port);
			if (auth != null && !"".equals(auth.trim())) {
				cache.auth(auth);
			}
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
