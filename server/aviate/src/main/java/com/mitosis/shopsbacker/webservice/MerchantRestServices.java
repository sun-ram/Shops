package com.mitosis.shopsbacker.webservice;

import java.io.IOException;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.MerchantResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Path("merchant")
public class MerchantRestServices<T> {
	final static Logger log = Logger.getLogger(MerchantRestServices.class
			.getName());

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	CustomerService<T> customerService;

	@Autowired
	UserService<T> userService;
	
	@Autowired
	AddressService<T> addessService;

	public AddressService<T> getAddessService() {
		return addessService;
	}

	public void setAddessService(AddressService<T> addessService) {
		this.addessService = addessService;
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

			JsonNode location = getLatLongByAddress(merchantVo);

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

			merchantImageUpload(merchantVo);

			Merchant merchant = setMerchant(merchantVo);
			
			merchantService.saveMerchant(merchant);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	public void merchantImageUpload(MerchantVo merchantVo) throws IOException,
			Exception {
		String merchantImagePath = "";
		String defaultImagePath = "";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		defaultImagePath = properties.getProperty("imagePath");
		merchantImagePath = "merchant/" + merchantVo.getName();

		String imageName = UUID.randomUUID().toString().replace("-", "");
		if (CommonUtil.uploadImage(merchantVo.getLogo().getImage(), merchantVo
				.getLogo().getType(), defaultImagePath+merchantImagePath, imageName)) {
			merchantVo.getLogo().setName(imageName);
			merchantVo.getLogo().setUrl(
					merchantImagePath+imageName + "." + merchantVo.getLogo().getType());
		}
	}

	public JsonNode getLatLongByAddress(MerchantVo merchantVo) {
		String full_address = merchantVo.getUser().getAddress().getAddress1()
				+ "," + merchantVo.getUser().getAddress().getAddress2() + ","
				+ merchantVo.getUser().getAddress().getCity() + ","
				+ merchantVo.getUser().getAddress().getState().getName() + ","
				+ merchantVo.getUser().getAddress().getCountry().getName() + ","
				+ merchantVo.getUser().getAddress().getPinCode();
		JsonNode location = CommonUtil.getLatLong(full_address);
		return location;
	}

	public Merchant setMerchant(MerchantVo merchantVo) throws Exception {
		Merchant merchant = (Merchant) CommonUtil
				.setAuditColumnInfo(Merchant.class.getName());
		merchant.setName(merchantVo.getName());

		Image image = setImage(merchantVo);
		merchant.setLogo(image);

		User user = setUser(merchantVo, merchant);
		merchant.setUser(user);
		return merchant;
	}

	public User setUser(MerchantVo merchantVo, Merchant merchant) throws Exception {
		User user = (User) CommonUtil.setAuditColumnInfo(User.class.getName());
		UserVo userVo = merchantVo.getUser();
		user.setName(userVo.getName());
		user.setUserName(userVo.getUserName());
		user.setPassword(userVo.getPassword());
		user.setEmailid(userVo.getEmailid());
		user.setRole(getRoleService()
				.getRole(RoleName.MerchantAdmin.toString()));
		user.setMerchant(merchant);
		merchant.setUser(user);
		AddressVo addressVo = userVo.getAddress();
		
		Address address = setAddress(addressVo);
		
		addessService.saveAddress(address);
		
		user.setAddress(address);
		
		return user;
	}

	public Address setAddress(AddressVo addressVo) throws Exception {
		Address address = (Address) CommonUtil.setAuditColumnInfo(Address.class.getName());
		address.setAddress1(addressVo.getAddress1());
		address.setAddress2(addressVo.getAddress2());
		address.setCity(addressVo.getCity());
		address.setPhoneNo(addressVo.getPhoneNo());
		address.setPinCode(addressVo.getPinCode());
		address.setLatitude(addressVo.getLatitude());
		address.setLongitude(addressVo.getLongitude());
		address.setCountry(addessService.getCountry(addressVo.getCountry().getCountryId()));
		address.setState(addessService.getStateById(addressVo.getState().getStateId()));
		return address;
	}

	public Image setImage(MerchantVo merchantVo) throws Exception {
		Image image = (Image) CommonUtil.setAuditColumnInfo(Image.class.getName());
		image.setName(merchantVo.getLogo().getName());
		image.setType(merchantVo.getLogo().getType());
		image.setUrl(merchantVo.getLogo().getUrl());
		return image;
	}

	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel updateMerchantDetails(MerchantVo merchantVo) {
		try {
			JsonNode location = getLatLongByAddress(merchantVo);
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
			if (merchantVo.getLogo().getImage() != null
					&& merchantVo.getLogo().getType() != null) {
				updateMerchantImage(merchantVo);
			}

			Merchant merchant = getMerchantService().getMerchantById(
					merchantVo.getMerchantId());

			setMerchant(merchant, merchantVo);

			getMerchantService().saveMerchant(merchant);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	public void updateMerchantImage(MerchantVo merchantVo) throws IOException,
			Exception {
		String merchantImagePath = "";
		String defaultImagePath = "";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		defaultImagePath = properties.getProperty("imagePath");
		merchantImagePath = "merchant/" + merchantVo.getName();
		if (CommonUtil.removeImage(defaultImagePath
				+ merchantVo.getLogo().getUrl())) {
			String imageName = UUID.randomUUID().toString().replace("-", "");
			if (CommonUtil.uploadImage(merchantVo.getLogo().getImage(),
					merchantVo.getLogo().getType(), defaultImagePath
							+ merchantImagePath, imageName)) {
				merchantVo.getLogo().setName(imageName);
				merchantVo.getLogo().setUrl(
						merchantImagePath + "/" + imageName + "."
								+ merchantVo.getLogo().getType());
			}
		}

	}

	public void setMerchant(Merchant merchant, MerchantVo merchantVo)
			throws Exception {
		merchant = (Merchant) CommonUtil.setAuditColumnInfo(Merchant.class
				.getName());
		merchant.setName(merchantVo.getName());

		Image image = setImage(merchantVo);
		merchant.setLogo(image);
		image.setMerchant(merchant);

		User user = setUser(merchantVo, merchant);
		merchant.setUser(user);
	}

	@Path("/getmerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MerchantResponseVo getMerchant() {
		MerchantResponseVo merchantResponse = new MerchantResponseVo();
		try {
			List<Merchant> merchants = getMerchantService().getMerchantList();
			for (Merchant merchant : merchants) {
				MerchantVo merchantVo = setMerchantVo(merchant);
				merchantResponse.getMerchant().add(merchantVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return merchantResponse;
	}

	public MerchantVo setMerchantVo(Merchant merchant) throws Exception {
		MerchantVo merchantVo = new MerchantVo();
		merchantVo.setName(merchant.getName());

		ImageVo imageVo = setImageVo(merchant);
		merchantVo.setLogo(imageVo);

		UserVo userVo = setUserVo(merchant);
		merchantVo.setUser(userVo);
		return merchantVo;
	}

	public UserVo setUserVo(Merchant merchant) {
		UserVo userVo = new UserVo();
		userVo.setName(merchant.getUser().getName());
		userVo.setUserName(merchant.getUser().getUserName());
		userVo.setPassword(merchant.getUser().getPassword());
		userVo.setEmailid(merchant.getUser().getEmailid());
		return userVo;
	}

	public ImageVo setImageVo(Merchant merchant) throws IOException {
		ImageVo imageVo = new ImageVo();
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		String imageUrl = properties.getProperty("imageUrl");
		imageVo.setName(merchant.getLogo().getName());
		imageVo.setType(merchant.getLogo().getType());
		imageVo.setUrl(imageUrl+merchant.getLogo().getUrl());
		return imageVo;
	}

	@Path("/deletemerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel deleteMerchant(MerchantVo merchant) {
		try {
			getMerchantService().deleteMerchant(merchant.getMerchantId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}
}