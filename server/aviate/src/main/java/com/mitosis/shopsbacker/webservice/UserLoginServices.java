package com.mitosis.shopsbacker.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.UserLoginResponseVo;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.customer.RoleVo;

@Path("user")
public class UserLoginServices<T> {

	ResponseModel response = new ResponseModel();

	@Autowired
	UserService<T> userService;

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserLoginResponseVo userLogin(UserVo userVo) {
		UserLoginResponseVo userLoginResponseVo = new UserLoginResponseVo();
		try {
			UserVo userDetails = new UserVo();
			if (userVo.getUserName() != null) {
				User user = userService.getUserByUserName(userVo.getUserName(),userVo.getPassword());
				if (user != null) {
					userDetails = setUserDetails(user);
					userLoginResponseVo.setUserVo(userDetails);
					userLoginResponseVo.setStatus(SBMessageStatus.SUCCESS
							.getValue());
				} else {
					userLoginResponseVo
							.setErrorCode(SBErrorMessage.INVALID_USERNAME
									.getCode());
					userLoginResponseVo
							.setErrorString(SBErrorMessage.INVALID_USERNAME
									.getMessage());
					userLoginResponseVo.setStatus(SBMessageStatus.FAILURE
							.getValue());
					return userLoginResponseVo;

				}
			}
		} catch (Exception e) {
			userLoginResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			userLoginResponseVo.setErrorString(e.toString());
			return userLoginResponseVo;
		}
		return userLoginResponseVo;
	}

	public UserVo setUserDetails(User user) {
		UserVo userDetails = new UserVo();
		userDetails.setUserId(user.getUserId());
		userDetails.setName(user.getName());
		userDetails.setImage(setUserImageDetails(user));
		userDetails.setMerchant(setMerchant(user));
		userDetails.setStore(setStore(user));
		userDetails.setEmailid(user.getEmailid());
		userDetails.setDeveiceid(user.getDeveiceid());
		userDetails.setUserName(user.getUserName());
		userDetails.setPhoneNo(user.getPhoneNo());
		userDetails.setRole(setRole(user));
		return userDetails;
	}

	public ImageVo setUserImageDetails(User user) {
		ImageVo image = new ImageVo();
		if (user.getImage() != null) {
			image.setName(user.getImage().getName());
			image.setType(user.getImage().getType());
			image.setUrl(user.getImage().getUrl());
		}
		return image;

	}

	public RoleVo setRole(User user) {
		RoleVo role = new RoleVo();
		if (user.getRole() != null) {
			role.setRoleId(user.getRole().getRoleId());
			role.setName(user.getRole().getName());
			role.setDescription(user.getRole().getDescription());
		}
		return role;

	}

	public MerchantVo setMerchant(User user) {
		MerchantVo merchantVo = new MerchantVo();
		if (user.getMerchant() != null) {
			merchantVo.setMerchantId(user.getMerchant().getMerchantId());
			merchantVo.setName(user.getMerchant().getName());
		}
		return merchantVo;

	}

	public StoreVo setStore(User user) {
		StoreVo storeVo = new StoreVo();
		if (user.getStore() != null) {
			storeVo.setStoreId(user.getStore().getStoreId());
			storeVo.setName(user.getStore().getName());
		}
		return storeVo;

	}

}
