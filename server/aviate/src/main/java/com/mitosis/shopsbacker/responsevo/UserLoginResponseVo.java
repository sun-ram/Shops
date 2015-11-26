package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.UserVo;

public class UserLoginResponseVo extends ResponseModel {

	public UserVo user;

	private List<UserVo> userVoList;

	public UserVo getUser() {
		 	return user;
		 	}

	public void setUser(UserVo user) {
		 		this.user = user;
		 	}

	public List<UserVo> getUserVoList() {
		return userVoList;
	}

	public void setUserVoList(List<UserVo> userVoList) {
		this.userVoList = userVoList;
	}

}
