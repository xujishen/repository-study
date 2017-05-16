package com.youdy.bean;

/**
 * 好友关系实体类
 * @File: UserFriendBean.java
 * @package com.youdy.bean
 * @author 宿继申 
 * @date: 2016年9月14日 上午10:09:18
 * @version 1.0
 * @Copyright (C) 2008-2016 www.oneapm.com. all rights reserved.
 *
 */
public class UserFriendBean extends CommonBean implements Comparable<UserFriendBean> {

	private static final long serialVersionUID = 1438638120130533150L;
	
	private Integer id;			// the unique flag
	
	private Integer userId;		// 用户id
	
	private Integer friendId;	// 好友id
	
	private String userName;
	
	private String friendName;	// 好友名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFriendId() {
		return friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserFriendBean other = (UserFriendBean) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserFriendBean [id=" + id + ", userId=" + userId + ", friendId=" + friendId + ", userName=" + userName
				+ ", friendName=" + friendName + "]";
	}

	@Override
	public int compareTo(UserFriendBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
