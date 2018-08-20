package com.yourboot.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Su Jishen on 2018/1/22 15:40.
 */
public final class IocUtil {
	
	static ApplicationContext applicationContext;
	
	static {
		try {
			applicationContext = new ClassPathXmlApplicationContext("classpath*:config/spring-servlet.xml");
		} catch (Exception e) {
			e.printStackTrace();;
		}
	}
	
	public static Object getService(String beanId) {
		return applicationContext.getBean(beanId);
	}
	
}
