package com.mitosis.shopsbacker.webservice;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.MerchantResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Path("merchant")
@Controller("merchantRestServices")
public class MerchantRestServices<T> {
	final static Logger log = Logger.getLogger(MerchantRestServices.class
			.getName());

	@Autowired(required = true)
	MerchantService<T> merchantService;

	@Autowired
	UserService<T> userService;

	@Autowired
	AddressService<T> addessService;
	
	@Autowired
	ImageService<T> imageService;

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
			User checkUniqueUser = getUserService().getUserByUserName(
					merchantVo.getUser().getUserName());
			if (checkUniqueUser != null) {
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
			Image img = null;

			Merchant merchant = merchantService.setMerchant(merchantVo, img);

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

		if (merchantVo.getLogo().getImage() == null) {
			return;
		}

		String merchantImagePath = "";
		String defaultImagePath = "";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		defaultImagePath = properties.getProperty("imagePath");
		merchantImagePath = "merchant/" + merchantVo.getName() + "/";

		String imageName = UUID.randomUUID().toString().replace("-", "");
		if (CommonUtil.uploadImage(merchantVo.getLogo().getImage(), merchantVo
				.getLogo().getType(), defaultImagePath + merchantImagePath,
				imageName)) {
			merchantVo.getLogo().setName(imageName);
			merchantVo.getLogo().setUrl(
					merchantImagePath + imageName + "."
							+ merchantVo.getLogo().getType());
		}
	}

	public JsonNode getLatLongByAddress(MerchantVo merchantVo) {
		String full_address = merchantVo.getUser().getAddress().getAddress1()
				+ "," + merchantVo.getUser().getAddress().getAddress2() + ","
				+ merchantVo.getUser().getAddress().getCity() + ","
				+ merchantVo.getUser().getAddress().getState().getName() + ","
				+ merchantVo.getUser().getAddress().getCountry().getName()
				+ "," + merchantVo.getUser().getAddress().getPinCode();
		JsonNode location = CommonUtil.getLatLong(full_address);
		return location;
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

			/*if(merchantVo.getLogo() != null && merchantVo.getLogo().getImage() != null){
				Merchant merchant = getMerchantService().getMerchantById(
						merchantVo.getMerchantId());
				
			}*/
			
			/*Merchant merchant = getMerchantService().getMerchantById(
					merchantVo.getMerchantId());*/
			Image img = null;
			
			
			merchantImageUpload(merchantVo);

			Merchant merchant = merchantService.setMerchant(merchantVo, img);

			getMerchantService().updateMerchant(merchant);
			
			if(merchant.getMerchantId() != null && img != null){
				
				imageService.deleteImage(img);
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	/*public void setMerchantForUpdate(Merchant merchant, MerchantVo merchantVo)
			throws Exception {
		merchant.setName(merchantVo.getName());

		setImageForUpdate(merchant, merchantVo);

		setUserForUpdate(merchantVo, merchant);
	}*/

	/*public void setImageForUpdate(Merchant merchant, MerchantVo merchantVo)
			throws Exception {
		Image image = merchant.getLogo();
		image.setName(merchantVo.getLogo().getName());
		image.setType(merchantVo.getLogo().getType());
		image.setUrl(merchantVo.getLogo().getUrl());
	}*/
	

	/*public void setUserForUpdate(MerchantVo merchantVo, Merchant merchant)
			throws Exception {
		User user = merchant.getUser();
		UserVo userVo = merchantVo.getUser();
		user.setName(userVo.getName());
		user.setUserName(userVo.getUserName());
		user.setPassword(userVo.getPassword());
		user.setEmailid(userVo.getEmailid());
		user.setPhoneNo(userVo.getPhoneNo());
		
		 * user.setRole(getRoleService()
		 * .getRole(RoleName.MerchantAdmin.toString()));
		 
		user.setMerchant(merchant);
		merchant.setUser(user);
		AddressVo addressVo = userVo.getAddress();
		Address address = setAddressForUpdate(addressVo, user);
		user.setAddress(address);
	}*/

	/*public Address setAddressForUpdate(AddressVo addressVo, User user)
			throws Exception {
		Address address = user.getAddress();
		address.setAddress1(addressVo.getAddress1());
		address.setAddress2(addressVo.getAddress2());
		address.setCity(addressVo.getCity());
		address.setPhoneNo(addressVo.getPhoneNo());
		address.setPinCode(addressVo.getPinCode());
		address.setLatitude(addressVo.getLatitude());
		address.setLongitude(addressVo.getLongitude());
		address.setCountry(addessService.getCountry(addressVo.getCountry()
				.getCountryId()));
		address.setState(addessService.getStateById(addressVo.getState()
				.getStateId()));
		return address;
	}*/

	@Path("/getmerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MerchantResponseVo getMerchant() {
		MerchantResponseVo merchantResponse = new MerchantResponseVo();
		try {
			List<Merchant> merchants = merchantService.getMerchantList();
			List<MerchantVo> listOfMerchantVo = new ArrayList<MerchantVo>();
			for (Merchant merchant : merchants) {
				MerchantVo merchantVo = merchantService.setMerchantVo(merchant);
				listOfMerchantVo.add(merchantVo);
			}
			merchantResponse.setMerchant(listOfMerchantVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			merchantResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			merchantResponse.setErrorString(e.getMessage());
		}
		return merchantResponse;
	}

	@Path("/deletemerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel deleteMerchant(MerchantVo merchant) {
		try {
			merchantService.deleteMerchant(merchant.getMerchantId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}
}