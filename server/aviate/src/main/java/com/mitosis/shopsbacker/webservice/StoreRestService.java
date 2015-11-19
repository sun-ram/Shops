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
			Store store = storeService.setStore(storeVo);
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
	public StoreResponseVo getShopListByAddress(AddressVo addressVo) {
		StoreResponseVo storeResponse = new StoreResponseVo();
		try {
			List<Store> stores = getStoreService().getShopList(addressVo.getCity(),addressVo.getAddress1());
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
}
