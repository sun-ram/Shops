package com.mitosis.shopsbacker.webservice;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.fasterxml.jackson.databind.JsonNode;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

@Path("merchant")
public class MerchantRestServices<T> {
	final static Logger log = Logger
			.getLogger(MerchantRestServices.class.getName());

	@Autowired
	Jaxb2Marshaller jaxb2Marshaller;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	CustomerService<T> customerService;

	@Autowired
	UserService<T> userService;

	public Jaxb2Marshaller getJaxb2Marshaller() {
		return jaxb2Marshaller;
	}

	public void setJaxb2Marshaller(Jaxb2Marshaller jaxb2Marshaller) {
		this.jaxb2Marshaller = jaxb2Marshaller;
	}

	public UserService<T> getUserService() {
		return userService;
	}

	public void setUserService(UserService<T> userService) {
		this.userService = userService;
	}

	public CustomerService<T> getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService<T> customerService) {
		this.customerService = customerService;
	}

	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}

	@Autowired
	RoleService<T> roleService;

	public RoleService<T> getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService<T> roleService) {
		this.roleService = roleService;
	}

	ResponseModel response = new ResponseModel();
	CommonUtil commonUtil = new CommonUtil();

	@Path("/addmerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addMerchantDetails(MerchantVo merchantVo) {
		try {
			List<Merchant> checkUniqueMerchants = getMerchantService()
					.getMerchantListByName(merchantVo.getName());
			if (!checkUniqueMerchants.isEmpty()) {
				response.setErrorCode(SBErrorMessage.METCHANT_NAME_ALREADY_EXIST
						.getCode());
				response.setErrorString(SBErrorMessage.METCHANT_NAME_ALREADY_EXIST
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}
			List<User> checkUniqueUser = getUserService().getUserByUserName(
					merchantVo.getName());
			if (!checkUniqueUser.isEmpty()) {
				response.setErrorCode(SBErrorMessage.USER_NAME_ALREADY_EXIST
						.getCode());
				response.setErrorString(SBErrorMessage.USER_NAME_ALREADY_EXIST
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}

			String full_address = merchantVo.getUser().getAddress()
					.getAddress1()
					+ ","
					+ merchantVo.getUser().getAddress().getAddress2()
					+ ","
					+ merchantVo.getUser().getAddress().getCity()
					+ ","
					+ merchantVo.getUser().getAddress().getState()
					+ ","
					+ merchantVo.getUser().getAddress().getCountryId()
					+ ","
					+ merchantVo.getUser().getAddress().getPinCode();
			JsonNode location = commonUtil.getLatLong(full_address);
			if (location == null) {
				response.setErrorCode(SBErrorMessage.INVALID_ADDRESS.getCode());
				response.setErrorString(SBErrorMessage.INVALID_ADDRESS
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}

			JsonNode loc = location.findValue("lat".toString());
			merchantVo.getUser().getAddress().setLatitude(loc.toString());
			loc = location.findValue("lng".toString());
			merchantVo.getUser().getAddress().setLongitude(loc.toString());

			String merchantImagePath = "";
			String defaultImagePath = "";
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			defaultImagePath = properties.getProperty("imagePath");
			merchantImagePath = "merchant" + defaultImagePath;

			String imageName = UUID.randomUUID().toString().replace("-", "");
			if (commonUtil.uploadImage(merchantVo.getLogo().getImage(),
					merchantVo.getLogo().getType(), merchantImagePath,
					imageName)) {
				merchantVo.getLogo().setName(imageName);
				merchantVo.getLogo().setUrl(
						imageName + "." + merchantVo.getLogo().getType());
			}

			Merchant merchant = setMerchant(merchantVo);
			getMerchantService().saveMerchant(merchant);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	public Merchant setMerchant(MerchantVo merchantVo) {
		Merchant merchant = new Merchant();
		merchant.setName(merchantVo.getName());

		Image image = setImage(merchantVo);
		merchant.setLogo(image);

		User user = setUser(merchantVo, merchant);
		merchant.setUser(user);
		return merchant;
	}

	public User setUser(MerchantVo merchantVo, Merchant merchant) {
		User user = new User();
		user.setName(merchantVo.getUser().getName());
		user.setUserName(merchantVo.getUser().getUserName());
		user.setPassword(merchantVo.getUser().getPassword());
		user.setEmailid(merchantVo.getUser().getEmailid());
		user.setRole(getRoleService()
				.getRole(RoleName.MerchantAdmin.toString()));
		user.setMerchant(merchant);
		return user;
	}

	public Image setImage(MerchantVo merchantVo) {
		Image image = new Image();
		image.setName(merchantVo.getLogo().getName());
		image.setType(merchantVo.getLogo().getType());
		image.setUrl(merchantVo.getLogo().getUrl());
		return image;
	}

	/*
	 * @Path("/getmerchant")
	 * 
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public MerchantResponseModel
	 * getMerchantList() { MerchantResponseModel merchantList = new
	 * MerchantResponseModel(); try { List<MerchantDetails> merchantDetails =
	 * merchantDAO .getMerchantList();
	 * merchantList.setMerchantList(merchantDetails);
	 * merchantList.setStatus(AVMessageStatus.SUCCESS.getValue()); } catch
	 * (Exception e) { e.printStackTrace(); log.error(e.getMessage()); response
	 * = CommonUtil.addStatusMessage(e, response); } return merchantList; }
	 * 
	 * @Path("/deletemerchant")
	 * 
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public ResponseModel
	 * deleteMerchant(JSONObject requestObj) { try {
	 * log.info("\n******************************************\n" +
	 * "Initializing the removce store service");
	 * merchantDAO.removeMerchant(Long.parseLong(requestObj
	 * .getString("merchantId")));
	 * response.setStatus(AVMessageStatus.SUCCESS.getValue());
	 * 
	 * } catch (Exception e) { e.printStackTrace(); log.error(e.getMessage());
	 * response = CommonUtil.addStatusMessage(e, response); }
	 * log.info("\n******************************************\n" +
	 * "Response of the remove store service");
	 * 
	 * return response; }
	 */

}
