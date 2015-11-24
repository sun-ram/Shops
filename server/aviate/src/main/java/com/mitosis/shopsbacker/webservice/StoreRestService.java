package com.mitosis.shopsbacker.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sun.security.action.GetLongAction;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Country;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.State;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.StoreResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;
import com.mitosis.shopsbacker.vo.common.CountryVo;
import com.mitosis.shopsbacker.vo.common.GeoLocation;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.common.StateVo;

/**
 * @author RiyazKhan
 *
 * @param <T>
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

	ResponseModel response = new ResponseModel();

	@Path("/addstore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addStoreDetails(StoreVo storeVo) {
		try {

			Merchant merchant = merchantService.getMerchantById(storeVo
					.getMerchant().getMerchantId());

			MerchantVo merchantVo = merchantService.setMerchantVo(merchant);
			storeVo.setMerchant(merchantVo);

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
			Store store = storeService.setStore(storeVo);
			store.getUser().setMerchant(merchant);
			store.setMerchant(merchant);
			storeService.saveStore(store);

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
	public ResponseModel updateStoreDetails(StoreVo storeVo) {
		try {

			Store store = storeService.setStore(storeVo);

			storeService.updateStore(store);

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
	public ResponseModel deleteStore(StoreVo storeVo) {
		try {
			Store store = getStoreService().getStoreById(storeVo.getStoreId());
			getStoreService().removeStore(store);
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
	public StoreResponseVo getStoreList(MerchantVo merchantVo) {
		StoreResponseVo storeResponse = new StoreResponseVo();
		try {
			Merchant merchant = merchantService.getMerchantById(merchantVo
					.getMerchantId());
			List<Store> stores = getStoreService().getStoreByMerchant(merchant);
			List<StoreVo> storeVoList = new ArrayList<StoreVo>();
			for (Store store : stores) {
				StoreVo storeVo = storeService.setStoreVo(store);
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
	public StoreResponseVo getStoreList() {
		StoreResponseVo storeResponse = new StoreResponseVo();
		try {
			List<Store> stores = getStoreService().getStoreList();
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

	@Path("/getstorelistbycity")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StoreResponseVo getStoreList(AddressVo addressVo) {
		StoreResponseVo storeResponse = new StoreResponseVo();
		try {
			List<Store> stores = getStoreService().getShopList(
					addressVo.getCity());
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

	@Path("/getshoplistbyaddress")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED)
	public StoreResponseVo getShopListByAddress(AddressVo addressVo) {
		StoreResponseVo storeResponse = new StoreResponseVo();
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
	@Transactional(propagation = Propagation.REQUIRED)
	public StoreResponseVo getStoreListBasedOnLoc(GeoLocation geoLocation)
			throws IOException {
		StoreResponseVo storeResponseVo = new StoreResponseVo();
		List<StoreVo> storeVoList = new ArrayList<StoreVo>();
		if (geoLocation.getLatitude() != null
				&& geoLocation.getLongitude() != null) {
			List<Store> storeList = storeService.getStoreList();
			for (Store store : storeList) {
				StoreVo storeVo = new StoreVo();
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
					StoreVo storeVo = new StoreVo();
					System.out.println(store);
					storeVo.setStoreId(store.getStoreId());
					storeVo.setName(store.getName());
					MerchantVo merchant = setMerchantDetails(store);
					UserVo user = setUserDetails(store);
					storeVo.setMerchant(merchant);
					storeVo.setUser(user);
					storeVoList.add(storeVo);
				}
				storeResponseVo.setStore(storeVoList);
				storeResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
				return storeResponseVo;
			} else {
				List<Store> storeList = storeService.getShopList(
						geoLocation.getCity(), geoLocation.getArea());
				for (Store store : storeList) {
					StoreVo storeVo = new StoreVo();
					System.out.println(store);
					storeVo.setStoreId(store.getStoreId());
					storeVo.setName(store.getName());
					MerchantVo merchant = setMerchantDetails(store);
					UserVo user = setUserDetails(store);
					storeVo.setMerchant(merchant);
					storeVo.setUser(user);
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
	@Transactional(propagation = Propagation.REQUIRED)
	public Set<String> getStoreByCity() {
		Set<String> cityList = null;
		try {
			cityList = new HashSet<String>(storeService.getShopCityList());
		} catch (Exception e) {
			System.out.println(e);
		}
		return cityList;

	}

	public MerchantVo setMerchantDetails(Store store) throws IOException {
		MerchantVo merchant = new MerchantVo();
		if (store.getMerchant() != null) {
			merchant.setMerchantId(store.getMerchant().getMerchantId());
			ImageVo imagevo = imageService.setImageVo(store.getMerchant());
			merchant.setLogo(imagevo);
		}
		return merchant;

	}

	public UserVo setUserDetails(Store store) throws IOException {
		UserVo user = new UserVo();
		if (store.getUser() != null) {
			user.setName(store.getUser().getName());
			if (store.getUser().getAddress() != null) {
				AddressVo address = new AddressVo();
				address.setAddress1(store.getUser().getAddress().getAddress1());
				address.setAddress2(store.getUser().getAddress().getAddress2());
				address.setCity(store.getUser().getAddress().getCity());
				user.setAddress(address);
			}
		}
		return user;

	}

}