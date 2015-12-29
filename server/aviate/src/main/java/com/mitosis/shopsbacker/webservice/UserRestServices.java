package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.CommonService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
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

	final static Logger log = Logger
			.getLogger(UserRestServices.class.getName());

	@Context
	private UriInfo uriInfo;

	ResponseModel response = new ResponseModel();

	@Autowired
	CommonService<T> commonService;

	@Autowired
	UserService<T> userService;

	@Autowired
	CustomerService<T> customerService;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	RoleService<T> roleService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	ImageService<T> imageService;

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String userLogin(UserVo userVo) throws Exception {
		UserLoginResponseVo userLoginResponseVo = new UserLoginResponseVo();
		boolean flag = false;
		String responseString = null;
		ObjectMapper mapper = CommonUtil.getObjectMapper();
		try {
			UserVo userDetails = new UserVo();
			if (userVo.getUserName() != null) {
				User user = userService.getUserByUserName(userVo.getUserName(), true);
				if (user != null) {
					flag = CommonUtil.passwordVerification(
							userVo.getPassword(), user.getPassword());
				}
				if (user != null && flag) {
					userDetails = setUserDetails(user);
					userLoginResponseVo.setUser(userDetails);
				} else {
					userLoginResponseVo
					.setErrorCode(SBErrorMessage.INVALID_USERNAME
							.getCode());
					userLoginResponseVo
					.setErrorString(SBErrorMessage.INVALID_USERNAME
							.getMessage());
					userLoginResponseVo.setStatus(SBMessageStatus.FAILURE
							.getValue());

					return responseString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginResponseVo);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			userLoginResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			userLoginResponseVo.setErrorString(e.toString());
			return   responseString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginResponseVo);
		}

		//mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		//mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			responseString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userLoginResponseVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		return responseString;
	}

	public UserVo setUserDetails(User user) throws Exception {  
		UserVo userDetails = new UserVo();
		userDetails.setUserId(user.getUserId());
		userDetails.setName(user.getName());
		userDetails.setImage(setUserImageDetails(user));
		userDetails.setMerchant(setMerchant(user));
		userDetails.setStore(setStore(user));
		userDetails.setEmailid(user.getEmailid());
		userDetails.setDeviceId(user.getDeviceId());
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

	public MerchantVo setMerchant(User user) throws Exception {
		MerchantVo merchantVo = new MerchantVo();
		Merchant merchant = user.getMerchant();
		if ( merchant!= null) {
			merchantVo.setMerchantId(merchant.getMerchantId());
			merchantVo.setName(merchant.getName());
			Image logo = merchant.getLogo();
			if(logo!=null){
				ImageVo logoVo=new ImageVo();
				logoVo.setUrl(imageService.generateMerchantImageUrl(logo.getUrl()));
				merchantVo.setLogo(logoVo);
			}
		}
		return merchantVo;

	}

	public StoreVo setStore(User user) {
		StoreVo storeVo = null;
		if (user.getStore() != null) {
			storeVo = new StoreVo();
			storeVo.setStoreId(user.getStore().getStoreId());
			storeVo.setName(user.getStore().getName());
		}
		return storeVo;

	}

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel saveUser(UserVo userVo) {
		response = new ResponseModel();
		try {

			User uniqueUser = userService.getUserByUserName(userVo.getUserName(), false);
			if (uniqueUser!=null) {
				response.setErrorCode(SBErrorMessage.USER_NAME_ALREADY_EXIST.getCode());
				response.setErrorString(SBErrorMessage.USER_NAME_ALREADY_EXIST
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}

			JsonNode location = getLatLongByAddress(userVo);

			if (location == null) {
				response.setErrorCode(SBErrorMessage.INVALID_ADDRESS.getCode());
				response.setErrorString(SBErrorMessage.INVALID_ADDRESS
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}

			JsonNode loc = location.findValue("lat".toString());
			userVo.getAddress().setLatitude(loc.toString());
			loc = location.findValue("lng".toString());
			userVo.getAddress().setLongitude(loc.toString());
			Store store = storeService.getStoreById(userVo.getStore()
					.getStoreId());
			User user = userService.setUser(userVo,
					roleService.getRole(userVo.getRole().getName()),userVo.getGenerate());
			user.setStore(store);
			user.setMerchant(store.getMerchant());
			userService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	public JsonNode getLatLongByAddress(UserVo userVo) {
		String full_address = userVo.getAddress().getAddress1() + ","
				+ userVo.getAddress().getAddress2() + ","
				+ userVo.getAddress().getCity().getName() + ","
				+ userVo.getAddress().getState().getName() + ","
				+ userVo.getAddress().getCountry().getName() + ","
				+ userVo.getAddress().getPinCode();
		Map<String, JsonNode> addressNodeMap=CommonUtil.getLatLong(full_address);
		JsonNode location = addressNodeMap.get("location");
		return location;
	}

	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel updateUser(UserVo userVo) {
		response = new ResponseModel();
		try {

			JsonNode location = getLatLongByAddress(userVo);

			if (location == null) {
				response.setErrorCode(SBErrorMessage.INVALID_ADDRESS.getCode());
				response.setErrorString(SBErrorMessage.INVALID_ADDRESS
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}
			JsonNode loc = location.findValue("lat".toString());
			userVo.getAddress().setLatitude(loc.toString());
			loc = location.findValue("lng".toString());
			userVo.getAddress().setLongitude(loc.toString());
			User user = userService.setUser(userVo,
					roleService.getRole(userVo.getRole().getName()), userVo.getGenerate());
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public EmployeeResponseVo getUser(JSONObject req) {
		EmployeeResponseVo response = new EmployeeResponseVo();
		try {
			List<String> roles = new ArrayList<String>();
			roles.add(RoleName.BACKER.toString());
			roles.add(RoleName.SHOPPER.toString());

			List<User> users = new ArrayList<User>();
			if (CommonUtil.isValidProperty(req, "storeId")) {
				users = userService.getUsers(roles, req.getString("storeId"));
			} else if (CommonUtil.isValidProperty(req, "merchantId")) {
				users = userService.getUsersByMerchantId(roles,
						req.getString("merchantId"));
			}
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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

	@Path("/getShoperDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserLoginResponseVo getShopperDetails(UserVo userVo) {
		UserLoginResponseVo userLoginResponseVo = new UserLoginResponseVo();
		List<UserVo> userVoList = new ArrayList<UserVo>();
		if(userVo.getStore()!=null){
			Store store = storeService.getStoreById(userVo.getStore()
					.getStoreId());
			if (store != null) {
				List<User> userList = userService.getUsers(null,store,RoleName.SHOPPER.toString());
				List<User> listOfUsers = userService.filterAssignedShoppers(store);
				for (User user : userList) {
					if(listOfUsers.contains(user)){
						continue;
					}
					UserVo userVoObj = new UserVo();
					userVoObj.setUserId(user.getUserId());
					userVoObj.setName(user.getName());
					userVoList.add(userVoObj);
				}
			}
			userLoginResponseVo.setUserVoList(userVoList);
			userLoginResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
			return userLoginResponseVo;
		}
		else{
			userLoginResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			return userLoginResponseVo;
		}

	}


	@Path("/getBackerDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserLoginResponseVo getBackerDetails(UserVo userVo) {
		UserLoginResponseVo userLoginResponseVo = new UserLoginResponseVo();
		List<UserVo> userVoList = new ArrayList<UserVo>();
		if (userVo.getMerchant()!=null) {
			Merchant merchant = merchantService.getMerchantById(userVo
					.getMerchant().getMerchantId());
			if (merchant != null) {
				List<User> userList = userService.getUser(merchant);
				for (User user : userList) {
					if (user.getRole().getName()
							.equalsIgnoreCase(RoleName.BACKER.toString())) {
						UserVo userVoObj = new UserVo();
						userVoObj.setUserId(user.getUserId());
						userVoObj.setName(user.getName());
						userVoList.add(userVoObj);
					}

				}
			}
			userLoginResponseVo.setUserVoList(userVoList);
			userLoginResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
			return userLoginResponseVo;
		} else if(userVo.getStore()!=null){
			Store store = storeService.getStoreById(userVo.getStore()
					.getStoreId());
			if (store != null) {
				List<User> userList = userService.getUser(store);
				for (User user : userList) {
					if (user.getRole().getName()
							.equalsIgnoreCase(RoleName.BACKER.toString())) {
						UserVo userVoObj = new UserVo();
						userVoObj.setUserId(user.getUserId());
						userVoObj.setName(user.getName());
						userVoList.add(userVoObj);
					}

				}
			}
			userLoginResponseVo.setUserVoList(userVoList);
			userLoginResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
			return userLoginResponseVo;
		}
		else{
			userLoginResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			return userLoginResponseVo;
		}

	}


	@Path("/deviceregister")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deviceRegister(UserVo userVo) {
		response = new ResponseModel();
		try {
			User user = userService.getUser(userVo.getUserId());
			if(userVo.getDeviceId() != null && userVo.getDeviceType() != null){
				user.setDeviceId(userVo.getDeviceId());
				user.setDeviceType(userVo.getDeviceType());
			}
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}


}
