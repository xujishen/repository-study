package com.youdy.constants;

/**
 * 系统属性枚举类
 * @File: SystemPropertiesEnum.java
 * @package com.youdy.constants
 * @author 宿继申 
 * @date: 2017年3月1日 上午10:21:35
 * @version 1.0
 * @Copyright (C) 2008-2017 www.oneapm.com. all rights reserved.
 *
 */
public enum SystemPropertiesEnum {
	
	UPLOAD_ROOT_PATH	(1, "upload_root_path", 	"上传根路径"),
	IMG_ROOT_PATH		(2, "img_root_path", 		"图片上传根路径"),
	VIDEO_ROOT_PATH		(3, "video_root_path", 		"视频上传根路径");
	
	/**
	 * 成员私有属性
	 */
	private int id;
	private String value;
	private String name;
	
	/**
	 * 私有构造器
	 * @param id
	 * @param value
	 * @param name
	 */
	private SystemPropertiesEnum(int id, String value, String name) {
		this.id = id;
		this.value = value;
		this.name = name;
	}

	/**
	 * getters
	 */
	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	
}
