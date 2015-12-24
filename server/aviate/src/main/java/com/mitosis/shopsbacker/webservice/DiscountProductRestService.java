package com.mitosis.shopsbacker.webservice;

/**
 * @author JAI BHARATHI.S
 */
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
import com.mitosis.shopsbacker.inventory.service.DiscountProductsService;
import com.mitosis.shopsbacker.inventory.service.DiscountService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.DiscountProduct;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.DiscountProductResponseVo;
import com.mitosis.shopsbacker.responsevo.DiscountResponseVo;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.inventory.DiscountProductsVo;
import com.mitosis.shopsbacker.vo.inventory.DiscountVo;

@Path("productdiscount")
@Controller("discountProductRestService")
public class DiscountProductRestService {
	@Autowired
	MerchantService<T> merchantService;
	
	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	ProductService<T> productService;
	
	@Autowired
	DiscountService<T> discountService;
	
	@Autowired
	DiscountProductsService<T> discountProductService;
	
	@Path("/savediscount")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addDiscountProduct(DiscountProductsVo discountProductVo) {
		ResponseModel response = new ResponseModel();
		try{
			if(discountProductVo.getDiscountList().size() !=0){
			for(DiscountVo discountVo: discountProductVo.getDiscountList()){	
				DiscountProduct discountProduct = new DiscountProduct();
				Merchant merchant = merchantService.getMerchantById(discountProductVo.getMerchant().getMerchantId());
				Discount discount = discountService.getDiscountById(discountVo.getDiscountId());
				discountProduct=discountProductService.setDiscountProduct(discountProductVo);
				discountProduct.setMerchant(merchant);
				discountProduct.setDiscount(discount);
				discountProductService.addDiscountProduct(discountProduct);
		        }
			}else{
				response.setStatus(SBMessageStatus.FAILURE.getValue());
			}
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		}catch (Exception e) {
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
	public ResponseModel deleteDiscount(DiscountProductsVo discountProduct) {
		ResponseModel response = new ResponseModel();
		
		try {
			DiscountProduct discountProducts = discountProductService.getDiscountProductById(discountProduct.getDiscountProductId());
			discountProductService.deleteDiscountProduct(discountProducts);
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
	public DiscountProductResponseVo getMerchantDiscountList(DiscountProductsVo productDiscount) {
		DiscountProductResponseVo discountProductResponse = new DiscountProductResponseVo();
		try {
			Discount discount = discountService.getDiscountById(productDiscount.getDiscount().getDiscountId());
			List<DiscountProduct> productList = discountProductService.getDiscountProductByDiscount(discount);
			for(DiscountProduct discountObj:productList){
				DiscountProductsVo discountProductVo = discountProductService.setDiscountVo(discountObj);
				discountProductResponse.getDiscountProductList().add(discountProductVo);
			}			
			discountProductResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			discountProductResponse.setErrorString(e.getMessage());
			discountProductResponse.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return discountProductResponse;
	}
	
	@Path("/getstorediscountList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DiscountResponseVo getStoreDiscountList(StoreVo storeVo) {
		DiscountResponseVo discountResponse = new DiscountResponseVo();
		try {
			Store store = storeService.getStoreById(storeVo.getStoreId());
			List<Discount> discountList = discountService.getAllDiscountByStore(store);
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
	
	@Path("/updatediscount")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public DiscountProductResponseVo updateDiscountProduct(DiscountProductsVo productDiscount) {
		DiscountProductResponseVo discountProductResponse = new DiscountProductResponseVo();
		try {
			Product product = productService.getProduct(productDiscount.getProduct().getProductId());
			DiscountProduct discountProduct = discountProductService.getDiscountProductById(productDiscount.getDiscountProductId());
			discountProduct.setProduct(product);
			discountProductService.updateDiscountProduct(discountProduct);
		} catch (Exception e) {
			e.printStackTrace();
			discountProductResponse.setErrorString(e.getMessage());
			discountProductResponse.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return discountProductResponse;
	}
	
}
