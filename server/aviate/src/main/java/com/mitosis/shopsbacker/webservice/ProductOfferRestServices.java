package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;

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
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.service.ProductOfferService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.responsevo.ProductOfferResponseVo;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferVo;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
@Path("productoffer")
@Controller("productOfferRestServices")
public class ProductOfferRestServices<T> {

	final static Logger log = Logger.getLogger(ProductInventoryRestService.class
			.getName());

	@Autowired
	ProductService<T> productService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	ProductOfferService<T> productOfferService;

	ProductOffer productOffer = null;

	public ProductService<T> getProductService() {
		return productService;
	}

	public void setProductService(ProductService<T> productService) {
		this.productService = productService;
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

	public ProductOfferService<T> getProductOfferService() {
		return productOfferService;
	}

	public void setProductOfferService(ProductOfferService<T> productOfferService) {
		this.productOfferService = productOfferService;
	}

	ResponseModel response = new ResponseModel();

	@Path("/addoffer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductOfferResponseVo addProductOffer(ProductOfferVo productOfferVo) {
		productOffer = new ProductOffer();
		ProductOfferResponseVo response = new ProductOfferResponseVo();
		try {
			productOffer=productOfferService.checkUniqueName(productOfferVo.getName());
			if(productOffer==null){
				Merchant merchant = merchantService.getMerchantById(productOfferVo.
						getMerchantVo().getMerchantId());
				Product product = productService.getProduct(productOfferVo.getProductVo().getProductId());
				productOffer = productOfferService.setProductOffer(productOfferVo,productOffer);
				productOffer.setMerchant(merchant);
				productOffer.setProduct(product);
				productOfferService.addProductOffer(productOffer);
				productOfferVo.setProductOfferId(productOffer.getProductOfferId());
				response.getProductOfferList().add(productOfferVo);
			}else{
				response.setStatus(SBMessageStatus.FAILURE.getValue());
				response.setErrorCode(SBErrorMessage.OFFER_NAME_ALREADY_EXIST.getCode());
				response.setErrorString(SBErrorMessage.OFFER_NAME_ALREADY_EXIST.getMessage());
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return response;

	}

	@Path("/updateoffer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel updateProductOffer(ProductOfferVo productOfferVo) {
		response = new ResponseModel();
		productOffer = new ProductOffer();
		try {
			productOffer = productOfferService.getProductOffer(productOfferVo.getProductOfferId());
			Merchant merchant = merchantService.getMerchantById(productOfferVo.
					getMerchantVo().getMerchantId());
			Product product = productService.getProduct(productOfferVo.getProductVo().getProductId());
			productOffer = productOfferService.setProductOffer(productOfferVo,productOffer);
			productOffer.setMerchant(merchant);
			productOffer.setProduct(product);
			productOfferService.updateProductOffer(productOffer);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return response;

	}

	@Path("/deleteoffer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteProductOffer(ProductOfferVo productOfferVo) {
		productOffer = new ProductOffer();
		try {
			productOffer = productOfferService.getProductOffer(productOfferVo.getProductOfferId());
			productOffer.setIsactive('N');
			productOfferService.updateProductOffer(productOffer);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return response;

	}

	@Path("/getofferbyMerchant")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductOfferResponseVo getProductOfferByMerchant(ProductOfferVo productOfferVo) {
		List<ProductOffer> productOfferList = new ArrayList<ProductOffer>();
		ProductOfferResponseVo productOfferVoList = new ProductOfferResponseVo();
		try {
			Merchant merchant = merchantService.getMerchantById(productOfferVo.
					getMerchantVo().getMerchantId());
			productOfferList = productOfferService.getProductOfferByMerchant(merchant);
			for(ProductOffer productOffer : productOfferList){
				productOfferVoList.getProductOfferList().add(productOfferService.setProductOfferVo(productOffer));
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return productOfferVoList;

	}
}
