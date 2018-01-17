package com.youdy.enums;

/**
 * 缓存业务前缀枚举类
 * 不同的业务模块前缀各异
 * Created by Su Jishen on 2018/1/17 17:20.
 */
public enum CachePrefixEnum {
	
	AREA_PREFIX("Area", "区域前缀", "-");
	
	private String prefix, name, seperate;  // 前缀, 名称, 分隔符
	CachePrefixEnum(final String prefix, final String name, final String desc) {
		this.prefix = prefix;
		this.name = name;
		this.seperate = desc;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSeperate() {
		return seperate;
	}
}
