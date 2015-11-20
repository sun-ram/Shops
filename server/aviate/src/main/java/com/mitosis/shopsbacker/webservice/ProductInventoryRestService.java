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
import com.mitosis.shopsbacker.inventory.service.ProductInventoryService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.ProductInventoryResponseVo;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductInventoryVo;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
@Path("productinventory")
@Controller("productInventoryRestServices")
public class ProductInventoryRestService<T> {
	
	final static Logger log = Logger.getLogger(ProductInventoryRestService.class
			.getName());

	@Autowired
	ProductInventoryService<T> productInventoryService;
	
	@Autowired
	MerchantService<T> merchantService;
	
	@Autowired
	StoreService<T> storeService;

	public ProductInventoryService<T> getProductInventoryService() {
		return productInventoryService;
	}

	public void setProductInventoryService(
			ProductInventoryService<T> productInventoryService) {
		this.productInventoryService = productInventoryService;
	}
	
	
	public MerchantService<T> getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService<T> merchantService) {
		this.merchantService = merchantService;
	}
	
	public StoreService<T> getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService<T> storeService) {
		this.storeService = storeService;
	}



	ResponseModel response = null;
	ProductInventoryResponseVo productInventoryResponse = null;
	
	@Path("/getproductinventory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductInventoryResponseVo getProductInventory(ProductInventoryVo productInventoryVo) {
		response = new ResponseModel();
		productInventoryResponse = new ProductInventoryResponseVo();
		try {
			if(productInventoryVo.getMerchantVo().getMerchantId()!=null){
				Merchant merchant = getMerchantService().getMerchantById(productInventoryVo.getMerchantVo().getMerchantId());
				List<ProductInventory> productInventoryList = getProductInventoryService().getProductInventoryByMerchant(merchant);
				for (ProductInventory productInventory : productInventoryList) {
					productInventoryVo = getProductInventoryService().setProductInventoryVo(productInventory);
					productInventoryResponse.getProductinventory().add(productInventoryVo);
				}
			}else if (productInventoryVo.getStorevo().getStoreId()!=null) {
				Store store = getStoreService().getStoreById(productInventoryVo.getStorevo().getStoreId());
				List<ProductInventory> productInventoryList = getProductInventoryService().getProductInventoryByStore(store);
				for (ProductInventory productInventory : productInventoryList) {
					productInventoryVo = getProductInventoryService().setProductInventoryVo(productInventory);
					productInventoryResponse.getProductinventory().add(productInventoryVo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return productInventoryResponse;
	}
	
}
