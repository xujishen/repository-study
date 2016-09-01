package com.youdy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 序列化工具类
 * @File: SerializeUtil.java
 * @package com.youdy.utils
 * @author 宿继申 
 * @date: 2016年8月30日 下午5:30:33
 * @version 1.0
 * @Copyright (C) 2008-2016 www.oneapm.com. all rights reserved.
 *
 */
public final class SerializeUtil {
	
	static Logger logger = Logger.getLogger(SerializeUtil.class.toGenericString());
	
	/**
	 * 序列化对象 - 将JVM内存中的java对象序列为可被外部识别的byte数组
	 * @param t
	 * @return
	 */
	public static <T> byte[] doSerialize(T t) {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject((Object) t);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.WARNING, "反序列化对象<" + t + ">失败, 原因: " + e.getMessage(), new Throwable());
			return null;
		}
		finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 反流化对象 - 将外部提过的byte数组序列化为JVM可识别的java对象
	 * @param t
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unSerialize(byte ... bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.WARNING, "反序列化对象<" + bytes + ">失败, 原因: " + e.getMessage(), new Throwable());
			return null;
		}
		finally {
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
