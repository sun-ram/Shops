package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Role Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RoleVo implements java.io.Serializable {

	private String roleId;
	private UserVo userByCreatedby;
	private UserVo userByUpdatedby;
	private String name;
	private String description;
	private char isactive;
	private Date created;
	private Date updated;
	private List<UserVo> users = new ArrayList<UserVo>();
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public UserVo getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(UserVo userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public UserVo getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(UserVo userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public List<UserVo> getUsers() {
		return users;
	}
	public void setUsers(List<UserVo> users) {
		this.users = users;
	}


}
