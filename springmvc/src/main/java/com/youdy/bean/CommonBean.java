package com.youdy.bean;

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
public class CommonBean implements Serializable, Cloneable {

	private static final long serialVersionUID = -7063485676785246066L;

	private Integer id;
	
	private Date createTime;
	
	private Integer creator;
	
	private String creatorName;
	
	private Date updateTime;
	
	private Integer updator;
	
	private String updatorName;
	
	private Integer pageNumber;
	
	private Integer pageCnt;

	private  Integer startIndex = 0;

	private Integer endIndex = 10;

	public Integer getStartIndex() {
		return startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	public String getUpdatorName() {
		return updatorName;
	}

	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(Integer pageCnt) {
		this.pageCnt = pageCnt;
	}

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
		return "CommonBean{" +
				"id=" + id +
				", createTime=" + createTime +
				", creator=" + creator +
				", creatorName='" + creatorName + '\'' +
				", updateTime=" + updateTime +
				", updator=" + updator +
				", updatorName='" + updatorName + '\'' +
				", pageNumber=" + pageNumber +
				", pageCnt=" + pageCnt +
				", startIndex=" + startIndex +
				", endIndex=" + endIndex +
				'}';
	}
}
