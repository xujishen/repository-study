package com.youdy.cache;

import java.io.Serializable;
import java.util.Date;

import com.youdy.bean.UserBean;
import com.youdy.utils.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class OneCache implements Serializable {
	
	private static final long serialVersionUID = 2023093286899452771L;

	private Jedis cache;
	
	// 无参构造方法
	public OneCache() {
		if (cache == null) {
			cache = new Jedis("127.0.0.1", 6379);
			//cache.auth("xujishen");
		}
	}
	
	@SuppressWarnings("unused")
	private Jedis getCache() {
		if (cache == null) {
			/*JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(10);
			config.setMaxWaitMillis(1000 * 10);
			config.setMinIdle(5);
			JedisPool pool = new JedisPool(config, "10.128.7.111", 6379, 100000);
			cache = pool.getResource();*/
			cache = new Jedis("10.128.7.111", 6379);
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
	
	public static void main(String[] args) {
		OneCache onec = new OneCache();
		for (int i = 0; i < 100; i ++) {
			UserBean ub = new UserBean();
			ub.setUserId(i);
			ub.setUserName("user" + i);
			ub.setAge((float) i);
			ub.setBirthday(new Date(new Date().getTime() - i * 1000 * 60 * 60 * 24));
			ub.setEmail("110" + i + "@163.com");
			ub.setWechatNum("wiii1110" + i);
			ub.setWechatOid(ub.getWechatNum() + "oid" + i);
			ub.setGender(i % 2 == 0 ? "0" : "1");
			ub.setGenderDesc(ub.getGender().equals("0") ? "女" : "男");
			ub.setQqNum("564391174" + i);
			ub.setTelephone("13800138000" + i);
			ub.setCreateTime(new Date());
			ub.setCreator(0);
			ub.setCreatorName("admin");
			//onec.set("user:" + ub.getUserId(), "Hello, I am the key " + i + ", how are u a ?!!  My baby " + i, "nx", "ex", 60 * 60);
			//onec.set("user:" + ub.getUserId(), SerializeUtil.doSerialize(ub), "nx", "ex", 60 * 60);
			byte[] bytes = onec.get("user:" + i);
			UserBean u = (UserBean) SerializeUtil.unSerialize(bytes);
		}
	}

}