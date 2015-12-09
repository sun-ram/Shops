package com.mitosis.shopsbacker.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.StoreResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.common.GeoLocation;
import com.mitosis.shopsbacker.vo.common.ImageVo;

/**
 * @author RiyazKhan
 *
 * @param <T>
 * 
 *            Reviewed by Sundaram 28/11/2015
 */
@Path("store")
@Controller("storeRestService")
public class StoreRestService<T> {
	final static Logger log = Logger
			.getLogger(StoreRestService.class.getName());

	@Autowired
	StoreService<T> storeService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	RoleService<T> roleService;

	@Autowired
	AddressService<T> addessService;

	@Autowired
	ImageService<T> imageService;

	@Autowired
	UserService<T> userService;

	ResponseModel response = null;
	User user = null;
	Merchant merchant = null;
	Store store = null;
	StoreResponseVo storeResponse, storeResponseVo = null;
	StoreVo storeVo = null;
	MerchantVo merchantVo = null;
	UserVo userVo = null;
	ImageVo imagevo = null;
	AddressVo addressVo = null;

	public UserService<T> getUserService() {
		return userService;
	}

	public void setUserService(UserService<T> userService) {
		this.userService = userService;
	}

	public AddressService<T> getAddessService() {
		return addessService;
	}

	public void setAddessService(AddressService<T> addessService) {
		this.addessService = addessService;
	}

	public RoleService<T> getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService<T> roleService) {
		this.roleService = roleService;
	}

	public StoreService<T> getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService<T> storeService) {
		this.storeService = storeService;
	}

	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}

	@Path("/addstore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addStoreDetails(StoreVo storeVo) {
		try {
			response = new ResponseModel();
			merchant = merchantService.getMerchantById(storeVo.getMerchant()
					.getMerchantId());

			List<Store> checkUniqueStore = getStoreService()
					.getStoreListByName(storeVo.getName(), merchant);

			if (!checkUniqueStore.isEmpty()) {
				response.setErrorCode(SBErrorMessage.STORE_NAME_ALREADY_EXIST
						.getCode());
				response.setErrorString(SBErrorMessage.STORE_NAME_ALREADY_EXIST
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}
			user = userService.getUserByUserName(storeVo.getUser()
					.getUserName());
			if (user != null) {
				response.setErrorCode(SBErrorMessage.USER_NAME_ALREADY_EXIST
						.getCode());
				response.setErrorString(SBErrorMessage.USER_NAME_ALREADY_EXIST
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}
			store = storeService.setStore(storeVo);
			store.getUser().setMerchant(merchant);
			store.setMerchant(merchant);
			storeService.saveStore(store);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
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
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel updateStoreDetails(StoreVo storeVo) {
		response = new ResponseModel();
		try {
			store = storeService.setStore(storeVo);
			storeService.updateStore(store);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);

		}
		return response;
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteStore(StoreVo storeVo) {
		response = new ResponseModel();
		try {
			store = getStoreService().getStoreById(storeVo.getStoreId());
			getStoreService().removeStore(store);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	@Path("/getstorebymerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public StoreResponseVo getStoreList(MerchantVo merchantVo) {
		storeResponse = new StoreResponseVo();
		try {
			merchant = merchantService.getMerchantById(merchantVo
					.getMerchantId());
			List<Store> stores = getStoreService().getStoreByMerchant(merchant);
			List<StoreVo> storeVoList = new ArrayList<StoreVo>();
			for (Store store : stores) {
				storeVo = storeService.setStoreVo(store);
				storeVoList.add(storeVo);
			}

			storeResponse.setStore(storeVoList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return storeResponse;
	}

	@Path("/getstorelist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public StoreResponseVo getStoreList() {
		storeResponse = new StoreResponseVo();
		try {
			List<Store> stores = getStoreService().getStoreList();
			List<StoreVo> storeVoList = new ArrayList<StoreVo>();
			for (Store store : stores) {
				storeVo = storeService.setStoreVo(store);
				storeVo.setMerchant(null);
				storeVoList.add(storeVo);
			}
			storeResponse.setStore(storeVoList);
		} catch (Exception e) {
			e.printStackTrace();
			storeResponse.setErrorString(e.getMessage());
			storeResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			log.error(e.getMessage());
		}
		return storeResponse;
	}

	@Path("/getstorelistbycity")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getStoreList(AddressVo addressVo) {
		String responseStr = "";
		storeResponse = new StoreResponseVo();
		try {
			List<Store> stores = getStoreService().getShopList(
					addressVo.getCity());
			for (Store store : stores) {
				storeVo = storeService.setStoreVo(store);
				storeResponse.getStore().add(storeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			storeResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			storeResponse.setErrorString(e.getMessage());
		}
		
		try {
			responseStr = CommonUtil.getObjectMapper(storeResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStr;
	}

	@Path("/getshoplistbyaddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public StoreResponseVo getShopListByAddress(AddressVo addressVo) {
		storeResponse = new StoreResponseVo();
		try {
			List<Store> stores = getStoreService().getShopList(
					addressVo.getCity(), addressVo.getAddress1());
			for (Store store : stores) {
				StoreVo storeVo = storeService.setStoreVo(store);
				storeResponse.getStore().add(storeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return storeResponse;
	}

	@Path("/getshoplist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public StoreResponseVo getStoreListBasedOnLoc(GeoLocation geoLocation)
			throws IOException {
		storeResponseVo = new StoreResponseVo();
		List<StoreVo> storeVoList = new ArrayList<StoreVo>();
		if (geoLocation.getLatitude() != null
				&& geoLocation.getLongitude() != null) {
			List<Store> storeList = storeService.getStoreList();
			for (Store store : storeList) {
				storeVo = new StoreVo();
				double dist;
				if (store.getUser().getAddress().getLatitude() != null
						&& store.getUser().getAddress().getLongitude() != null) {
					dist = CommonUtil.distance(
							Double.parseDouble(geoLocation.getLatitude()),
							Double.parseDouble(geoLocation.getLongitude()),
							Double.parseDouble(store.getUser().getAddress()
									.getLatitude()),
							Double.parseDouble(store.getUser().getAddress()
									.getLongitude()));
					if (dist <= 40) {
						storeVo.setStoreId(store.getStoreId());
						storeVo.setName(store.getName());
						MerchantVo merchant = setMerchantDetails(store);
						storeVo.setMerchant(merchant);
						storeVoList.add(storeVo);
					}
				}

			}
			storeResponseVo.setStore(storeVoList);
			storeResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
			return storeResponseVo;
			/* based on the geo location search - with getting storeList */
		} else {
			/* Based on the city and country search */
			if (geoLocation.getCity() != null && geoLocation.getArea() == null) {
				List<Store> storeList = storeService.getShopList(geoLocation
						.getCity());
				for (Store store : storeList) {
					storeVo = new StoreVo();
					System.out.println(store);
					storeVo.setStoreId(store.getStoreId());
					storeVo.setName(store.getName());
					merchantVo = setMerchantDetails(store);
					userVo = setUserDetails(store);
					storeVo.setMerchant(merchantVo);
					storeVo.setUser(userVo);
					storeVoList.add(storeVo);
				}
				storeResponseVo.setStore(storeVoList);
				storeResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
				return storeResponseVo;
			} else {
				List<Store> storeList = storeService.getShopList(
						geoLocation.getCity(), geoLocation.getArea());
				for (Store store : storeList) {
					storeVo = new StoreVo();
					System.out.println(store);
					storeVo.setStoreId(store.getStoreId());
					storeVo.setName(store.getName());
					merchantVo = setMerchantDetails(store);
					userVo = setUserDetails(store);
					storeVo.setMerchant(merchantVo);
					storeVo.setUser(userVo);
					storeVoList.add(storeVo);
				}
				storeResponseVo.setStore(storeVoList);
				storeResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
				return storeResponseVo;
			}
		}

	}

	@Path("/getcity")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getStoreByCity() {
		storeResponse = new StoreResponseVo();
		String responseStr = "";
		try {
			SortedSet<String>  cityList = new TreeSet<String>(storeService.getShopCityList());
			storeResponse.setCityList(cityList);
		} catch (Exception e) {
			e.printStackTrace();
			storeResponse.setErrorString(e.getMessage());
			storeResponse.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(storeResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseStr;
	}

	public MerchantVo setMerchantDetails(Store store) throws IOException {
		MerchantVo merchantVo = new MerchantVo();
		Merchant merchantObj = store.getMerchant();
		if (merchantObj != null) {
			merchantVo.setMerchantId(merchantObj.getMerchantId());
			merchantVo.setName(merchantObj.getName());
			imagevo = imageService.setImageVo(merchantObj);
			merchantVo.setLogo(imagevo);
		}
		return merchantVo;

	}

	public UserVo setUserDetails(Store store) throws IOException {
		userVo = new UserVo();
		if (store.getUser() != null) {
			userVo.setName(store.getUser().getName());
			if (store.getUser().getAddress() != null) {
				addressVo = new AddressVo();
				addressVo.setAddress1(store.getUser().getAddress()
						.getAddress1());
				addressVo.setAddress2(store.getUser().getAddress()
						.getAddress2());
				addressVo.setCity(store.getUser().getAddress().getCity());
				userVo.setAddress(addressVo);
			}
		}
		return userVo;

	}

}