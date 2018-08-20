package com.yourboot.enums;

/**
 * 业务与缓存数据库关系
 * Created by Su Jishen on 2018/1/17 16:48.
 */
public enum CacheDbEnum {
	
	AREA_DB(0, "区域DB", "存储区域的数据库"),

	AREA_ID_DB(1, "区域ID", "存储区域ID的数据库");

	private int dbIndex;
	private String dbName, desc;
	
	public int getDbIndex() {
		return dbIndex;
	}
	
	public String getDbName() {
		return dbName;
	}
	
	public String getDesc() {
		return desc;
	}
	
	CacheDbEnum(int dbIndex, String dbName, String desc) {
		this.dbIndex = dbIndex;
		this.dbName = dbName;
		this.desc = desc;
	}
}
