package com.youdy.bean;

/**
 * 系统区域实体Bean
 * @File: SysAreaBean.java
 * @package com.youdy.bean
 * @author 宿继申 
 * @date: 2016年10月17日 下午3:53:21
 * @version 1.0
 * @Copyright (C) 2008-2016 www.oneapm.com. all rights reserved.
 *
 */
public class SysAreaBean extends CommonBean {

	private static final long serialVersionUID = -5505278375125054618L;
	
	private Integer areaID;
	private String areaName;
	private Integer areaCode;
	private Integer parentID;
	private Integer areaLevel;
	private Integer countryID;
	private Short status;
	
	private transient String areaSearchName;
	
	//private Date createTime;
	public Integer getAreaID() {
		return areaID;
	}
	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}
	public Integer getParentID() {
		return parentID;
	}
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	public Integer getAreaLevel() {
		return areaLevel;
	}
	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}
	public Integer getCountryID() {
		return countryID;
	}
	public void setCountryID(Integer countryID) {
		this.countryID = countryID;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	public String getAreaSearchName() {
		return areaSearchName;
	}
	public void setAreaSearchName(String areaSearchName) {
		this.areaSearchName = areaSearchName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaID == null) ? 0 : areaID.hashCode());
		result = prime * result + ((areaName == null) ? 0 : areaName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysAreaBean other = (SysAreaBean) obj;
		if (areaID == null) {
			if (other.areaID != null)
				return false;
		} else if (!areaID.equals(other.areaID))
			return false;
		if (areaName == null) {
			if (other.areaName != null)
				return false;
		} else if (!areaName.equals(other.areaName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SysAreaBean [areaID=" + areaID + ", areaName=" + areaName + ", areaCode=" + areaCode + ", parentID="
				+ parentID + ", areaLevel=" + areaLevel + ", countryID=" + countryID + ", status=" + status + "]";
	}
	
}
