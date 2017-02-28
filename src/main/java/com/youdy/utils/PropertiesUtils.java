package com.youdy.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public final class PropertiesUtils {
	
	private static Properties properties;
	
	private static String EMPTY_STRING = "";
	
	/**
	 * 私有构造器
	 */
	private PropertiesUtils() {
		
	}
	
	/**
	 * 初始化域
	 */
	static {
		initProperties();
	}

	/**
	 * 初始化系统属性
	 */
	private static void initProperties() {
		LocalizedResourceHelper helper = new LocalizedResourceHelper();
		Resource r = helper.findLocalizedResource("config/system", ".properties", null);
		try {
			properties = PropertiesLoaderUtils.loadProperties(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 根据key获取属性值
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		if (key == null || EMPTY_STRING.equals(key.trim())) {
			return EMPTY_STRING;
		}
		return properties.getProperty(key);
	}
	
}
