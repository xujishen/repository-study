package com.youdy.bean;

import java.util.Date;

/**
 * 用户实体类
 * @File: UserBean.java
 * @package com.youdy.bean
 * @author 宿继申 
 * @date: 2016年9月14日 上午10:09:46
 * @version 1.0
 * @Copyright (C) 2008-2016 www.oneapm.com. all rights reserved.
 *
 */
public class UserBean extends CommonBean implements Comparable<UserBean> {

	private static final long serialVersionUID = 1438638120130533150L;
	
	private Integer userId;
	
	private String userName;

	private Float age;
	
	private String gender;
	
	private String genderDesc;
	
	private Date birthday;
	
	private String telephone;
	
	private String wechatNum;
	
	private String wechatOid;
	
	private String qqNum;
	
	private String email;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGenderDesc() {
		return genderDesc;
	}

	public void setGenderDesc(String genderDesc) {
		this.genderDesc = genderDesc;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWechatNum() {
		return wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public String getWechatOid() {
		return wechatOid;
	}

	public void setWechatOid(String wechatOid) {
		this.wechatOid = wechatOid;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserBean other = (UserBean) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userName=" + userName + ", age=" + age + ", gender=" + gender
				+ ", genderDesc=" + genderDesc + ", birthday=" + birthday + ", telephone=" + telephone + ", wechatNum="
				+ wechatNum + ", wechatOid=" + wechatOid + ", qqNum=" + qqNum + ", email=" + email + "]";
	}

	@Override
	public int compareTo(UserBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
