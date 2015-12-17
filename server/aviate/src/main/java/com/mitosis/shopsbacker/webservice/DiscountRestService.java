package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.service.DiscountService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.DiscountResponseVo;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.inventory.DiscountVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;


/**
 * @author RiyazKhan
 *
 * @param <T>
 */
@Path("discount")
@Controller("discountRestService")
public class DiscountRestService {
	
	@Autowired
	MerchantService<T> merchantService;
	
	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	ProductService<T> productService;
	
	@Autowired
	DiscountService<T> discountService;
	
	@Path("/savediscount")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addDiscount(DiscountVo discountVo) {
		ResponseModel response = new ResponseModel();
		try{
			if(discountVo.getStoreList().size() !=0){
			List<StoreVo> storeVos = discountVo.getStoreList();
			for(StoreVo storevo: storeVos){	
				Store store = storeService.getStoreById(storevo.getStoreId());
				Merchant merchant = store.getMerchant();
			List<Discount> checkUniqueDiscount = discountService.getUniqeName(discountVo.getName());
			if (checkUniqueDiscount.isEmpty()) {
			Discount discount = discountService.setDiscount(discountVo);
			discount.setStore(store);
			discount.setMerchant(merchant);
			discountService.addDiscount(discount);
			}else{
				
				Discount discount = discountService.setDiscount(discountVo);
				discount.setStore(store);
				discount.setMerchant(merchant);
				discountService.updateDiscount(discount);
				
				
			}
		}
	}
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	
	@Path("/deletediscount")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteDiscount(DiscountVo discountVo) {
		ResponseModel response = new ResponseModel();
		
		try {
			Discount discount = discountService.getDiscountById(discountVo.getDiscountId());
			discountService.deleteDiscount(discount);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorString(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
	}
	
	@Path("/getdiscountbyid")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DiscountResponseVo getDiscountById(DiscountVo discountVo) {
		DiscountResponseVo discountResponse = new DiscountResponseVo();
		try {
			
			Discount discount = discountService.getDiscountById(discountVo.getDiscountId());
			
			discountResponse.setDiscount(discountService.setDiscountVo(discount));
			discountResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {

			e.printStackTrace();
			discountResponse.setErrorString(e.getMessage());
			discountResponse.setStatus(SBMessageStatus.FAILURE.getValue());
		
		}
		return discountResponse;
	}
	
	@Path("/getmerchantdiscountList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DiscountResponseVo getMerchantDiscountList(MerchantVo merchantVo) {
		DiscountResponseVo discountResponse = new DiscountResponseVo();
		try {
			Merchant merchant = merchantService.getMerchantById(merchantVo.getMerchantId());
			List<Discount> discountList = discountService.getAllDiscountByMerchant(merchant);
			List<DiscountVo> discountVoList = new ArrayList<DiscountVo>();
			for(Discount discount:discountList){
				
				DiscountVo discountVo = discountService.setDiscountVo(discount);
				discountVoList.add(discountVo);
			}			
			discountResponse.setDiscountVos(discountVoList);
			discountResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {

			e.printStackTrace();
			discountResponse.setErrorString(e.getMessage());
			discountResponse.setStatus(SBMessageStatus.FAILURE.getValue());
		
		}
		return discountResponse;
	}

}
