package com.youdy.bean;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础bean
 * @File: CommonBean.java
 * @package com.youdy.bean
 * @author 宿继申 
 * @date: 2016年8月30日 下午4:48:41
 * @version 1.0
 * @Copyright (C) 2008-2016 www.oneapm.com. all rights reserved.
 *
 */
@Data
@ToString
public class CommonBean implements Serializable, Cloneable {

	private static final long serialVersionUID = -7063485676785246066L;

	private Integer id;
	
	private Date createTime;
	
	private Integer creator;
	
	private String creatorName;
	
	private Date updateTime;
	
	private Long createTimeLong;
	
	private Long updateTimeLong;
	
	private Integer updator;
	
	private String updatorName;
	
	private Integer pageNum = 1;
	
	private Integer pageSize = 10;

	private  Integer startIndex = 0;

	private Integer endIndex = 10;

	private Integer cacheDbNum = 0;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CommonBean)) return false;

		CommonBean that = (CommonBean) o;

		return getId().equals(that.getId());

	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
