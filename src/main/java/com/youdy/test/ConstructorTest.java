package com.youdy.test;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import sun.reflect.ConstructorAccessor;
import sun.reflect.ReflectionFactory;

/**
 * 通过反射创建实例 Created by Su Jishen on 2017/1/11 11:17.
 */
public class ConstructorTest {

	private static final String USER_NAME = "";

	private static final Map<String, Object> MAP = new LinkedHashMap<>();

	private final Integer INTEGER = BigDecimal.ZERO.intValue();

	volatile transient int serialId = 0;

	void fuck() {
		System.out.println();
	}
	
	public static void printType(TestType testType) {
		System.out.println(testType.ordinal());
	}

	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("com.youdy.test.TestType");
		Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, int.class);
		ReflectionFactory reflection = ReflectionFactory.getReflectionFactory();
		ConstructorAccessor constructorAccessor = reflection.newConstructorAccessor(constructor);
		TestType t = (TestType) constructorAccessor.newInstance(new Object[]{"Type", 3});
		ConstructorTest.printType(t);
		System.out.println(clazz.getEnumConstants());
	}

}

enum TestType {
	TYPE1, TYPE2;
	
};
