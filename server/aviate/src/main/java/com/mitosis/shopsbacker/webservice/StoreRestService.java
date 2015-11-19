package com.mitosis.shopsbacker.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.common.service.AddressService;
import com.mitosis.shopsbacker.model.Address;
import com.mitosis.shopsbacker.model.Country;
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
import com.mitosis.shopsbacker.vo.common.StateVo;

/**
 * @author RiyazKhan
 *
 * @param <T>
 */
@Path("store")
@Controller("storeRestService")
public class StoreRestService<T> {
	final static Logger log = Logger.getLogger(StoreRestService.class
			.getName());

	@Autowired
	StoreService<T> storeService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	RoleService<T> roleService;
	
	@Autowired
	AddressService<T> addessService;

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

	public Store setStore(StoreVo storeVo) throws Exception {
		Store store = (Store) CommonUtil
				.setAuditColumnInfo(Store.class.getName());
		store.setName(storeVo.getName());

		UserVo userVo = storeVo.getUser();
		User user = setUser(userVo);
		user.setStore(store);
		
		MerchantVo merchantVo = storeVo.getMerchant();
 		//Merchant merchant = merchantService.setMerchant(merchantVo);

		store.setUser(user);
		//store.setMerchant(merchant);
		return store;
	}
	

	public void setStore(Store store, StoreVo storeVo)
			throws Exception {
		store = (Store) CommonUtil.setAuditColumnInfo(Store.class
				.getName());
		store.setName(storeVo.getName());

		UserVo userVo = storeVo.getUser();
		User user = setUser(userVo);
		user.setStore(store);
		store.setUser(user);
	}
	
	public StoreVo setStoreVo(Store store) throws Exception {
		StoreVo storeVo = new StoreVo();
		storeVo.setName(store.getName());
		User user = store.getUser();
		UserVo userVo = setUserVo(user);
		storeVo.setUser(userVo);
		return storeVo;
	}
	
	public UserVo setUserVo(User user) throws Exception {
		UserVo userVo = new UserVo();
		userVo.setName(user.getName());
		userVo.setUserName(user.getUserName());
		userVo.setPassword(user.getPassword());
		userVo.setEmailid(user.getEmailid());
		userVo.setPhoneNo(user.getPhoneNo());
		userVo.setUserId(user.getUserId());
		userVo.setAddress(setAddressVo(user.getAddress()));
		return userVo;
	}
	
	public AddressVo setAddressVo(Address address) throws Exception {
		AddressVo addressVo = new AddressVo();
		addressVo.setAddress1(address.getAddress1());
		addressVo.setAddress2(address.getAddress2());
		addressVo.setCity(address.getCity());
		addressVo.setPhoneNo(address.getPhoneNo());
		addressVo.setPinCode(address.getPinCode());
		addressVo.setLatitude(address.getLatitude());
		addressVo.setLongitude(address.getLongitude());
		addressVo.setCountry(setCountryVo(address.getCountry()));
		addressVo.setState(setCountryVo(address.getState()));
		return addressVo;
	}
	
	public CountryVo setCountryVo(Country country){
		CountryVo countryVo = new CountryVo();
		countryVo.setName(country.getName());
		countryVo.setCountryId(country.getCountryId());
		countryVo.setCurrencyCode(country.getCurrencyCode());
		countryVo.setCurrencyName(country.getCurrencyName());
		return countryVo;
	}
	
	public StateVo setCountryVo(State state){
		StateVo stateVo = new StateVo();
		stateVo.setName(state.getName());
		stateVo.setStateId(state.getStateId());
		return stateVo;
	}

	public User setUser(UserVo userVo) throws Exception {
		User user = (User) CommonUtil.setAuditColumnInfo(User.class.getName());
		user.setName(userVo.getName());
		user.setUserName(userVo.getUserName());
		user.setPassword(CommonUtil.passwordEncoder(userVo.getPassword()));
		user.setEmailid(userVo.getEmailid());
		user.setPhoneNo(userVo.getPhoneNo());
		user.setRole(getRoleService()
				.getRole(RoleName.MERCHANTADMIN.toString()));
		AddressVo addressVo = userVo.getAddress();
		
		Address address = setAddress(addressVo);
		
		//addessService.saveAddress(address);
		
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
	@Path("/addstore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addStoreDetails(StoreVo storeVo) {
		try {

			Merchant merchant = merchantService.getMerchantById(storeVo.getMerchant().getMerchantId());
			
			MerchantVo merchantVo = merchantService.setMerchantVo(merchant);
			storeVo.setMerchant(merchantVo);

			List<Store> checkUniqueStore = getStoreService()
					.getStoreListByName(storeVo.getName(),merchant);

			if (!checkUniqueStore.isEmpty()) {
				response.setErrorCode(SBErrorMessage.STORE_NAME_ALREADY_EXIST
						.getCode());
				response.setErrorString(SBErrorMessage.STORE_NAME_ALREADY_EXIST
						.getMessage());
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				return response;
			}

			Store store = setStore(storeVo);

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
			
			Store store = getStoreService().getStoreById(
					storeVo.getStoreId());
			
			setStore(store, storeVo);

			storeService.saveStore(store);
			
		}catch(Exception e) {
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
	
	
	@Path("/getshoplist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StoreResponseVo getShopList(AddressVo addressVo) {
		StoreResponseVo storeResponse = new StoreResponseVo();
		try {
			List<Store> stores = getStoreService().getShopList(addressVo.getCity());
			for (Store store : stores) {
				StoreVo storeVo = setStoreVo(store);
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
	public StoreResponseVo getShopListByAddress(AddressVo addressVo) {
		StoreResponseVo storeResponse = new StoreResponseVo();
		try {
			List<Store> stores = getStoreService().getShopList(addressVo.getCity(),addressVo.getAddress1());
			for (Store store : stores) {
				StoreVo storeVo = setStoreVo(store);
				storeResponse.getStore().add(storeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return storeResponse;
	}
}
