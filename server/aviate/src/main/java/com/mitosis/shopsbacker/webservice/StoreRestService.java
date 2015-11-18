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
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.responsevo.StoreResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;

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

		MerchantRestServices<T> merchantRestServices = new MerchantRestServices<T>();
		UserVo userVo = storeVo.getUser();
		User user = merchantRestServices.setUser(userVo);
		user.setStore(store);
		store.setUser(user);
		return store;
	}
	
	public void setStore(Store store, StoreVo storeVo)
			throws Exception {
		store = (Store) CommonUtil.setAuditColumnInfo(Store.class
				.getName());
		store.setName(storeVo.getName());

		UserVo userVo = storeVo.getUser();
		MerchantRestServices<T> merchantRestServices = new MerchantRestServices<T>();
		User user = merchantRestServices.setUser(userVo);
		user.setStore(store);
		store.setUser(user);
	}
	
	public StoreVo setStoreVo(Store store) throws Exception {
		StoreVo storeVo = new StoreVo();
		storeVo.setName(store.getName());
		User user = store.getUser();
		MerchantRestServices<T> merchantRestServices = new MerchantRestServices<T>();
		UserVo userVo = merchantRestServices.setUserVo(user);
		storeVo.setUser(userVo);
		return storeVo;
	}


	@Path("/addstore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel addStoreDetails(StoreVo storeVo) {
		try {

			Merchant merchant = merchantService.getMerchantById(storeVo.getMerchant().getMerchantId());

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
