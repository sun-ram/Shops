package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.EmployeeResponseVo;
import com.mitosis.shopsbacker.responsevo.UserLoginResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.customer.RoleVo;

@Path("user")
@Controller("userRestServices")
public class UserRestServices<T> {
	
	final static Logger log = Logger.getLogger(UserRestServices.class
			.getName());
	
	ResponseModel response = new ResponseModel();

	@Autowired
	UserService<T> userService;
	
	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	RoleService<T> roleService;

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserLoginResponseVo userLogin(UserVo userVo) {
		UserLoginResponseVo userLoginResponseVo = new UserLoginResponseVo();
		boolean flag = false;
		try {
			UserVo userDetails = new UserVo();
			if (userVo.getUserName() != null) {
				User user = userService.getUserByUserName(userVo.getUserName());
				if (user != null) {
					flag = CommonUtil.passwordVerification(
							userVo.getPassword(), user.getPassword());
				}
				if (user != null && flag) {
					userDetails = setUserDetails(user);
					userLoginResponseVo.setUserVo(userDetails);
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
			e.printStackTrace();
			log.error(e.getMessage());
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

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel saveUser(UserVo userVo) {
		try {
			Store store = storeService.getStoreById(userVo.getStore().getStoreId());
			//TODO need to set data from userVo to user Entity
			User user = userService.setUser(userVo, roleService.getRole(userVo.getRole().getName()));
			user.setStore(store);
			userService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateUser(UserVo userVo) {
		try {
			//TODO we need to implement Set entity to vo
			User user = userService.setUser(userVo, roleService.getRole(userVo.getRole().getName()));
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	@Path("/getemployees")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeResponseVo getUser(JSONObject req) {
		EmployeeResponseVo response = new EmployeeResponseVo();
		try {
			List<String> roles = new ArrayList<String>();
			roles.add(RoleName.BACKER.toString());
			roles.add(RoleName.SHOPPER.toString());
			List<User> users = userService.getUsers(roles,
					req.getString("storeId"));
			List<UserVo> usersVo = new ArrayList<UserVo>();
			for (User usr : users) {
				usersVo.add(userService.setUserVo(usr));
			}
			response.setUsers(usersVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return response;
	}

	@Path("/deleteemployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel deleteUser(JSONObject req) {
		try {
			userService.deleteUser(req.getString("userId"));
		} catch (JSONException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

}
