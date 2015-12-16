package com.mitosis.shopsbacker.webservice;

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

import com.mitosis.shopsbacker.inventory.service.ProductOfferLineService;
import com.mitosis.shopsbacker.inventory.service.ProductOfferService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductOfferLine;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferLineVo;

/**
 * @author JAI BHARATHI
 *
 * @param <T>
 */
@Path("productofferline")
@Controller("productOfferLineRestServices")
public class ProductOfferLineRestService<T> {
	
	final static Logger log = Logger.getLogger(ProductOfferLineRestService.class
			.getName());
	
	@Autowired
	ProductService<T> productService;
	
	@Autowired
	ProductOfferService<T> productOfferService;
	

	@Autowired
	ProductOfferLineService productOfferLineService;
	
	
	public ProductOfferLineService getProductOfferLineService() {
		return productOfferLineService;
	}

	public void setProductOfferLineService(
			ProductOfferLineService productOfferLineService) {
		this.productOfferLineService = productOfferLineService;
	}

	ProductOfferLine productOfferLine = null;
	
	public ProductService<T> getProductService() {
		return productService;
	}

	public void setProductService(ProductService<T> productService) {
		this.productService = productService;
	}

	public ProductOfferService<T> getProductOfferService() {
		return productOfferService;
	}

	public void setProductOfferService(ProductOfferService<T> productOfferService) {
		this.productOfferService = productOfferService;
	}
	
	ResponseModel response = new ResponseModel();
	
	@Path("/addofferline")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addProductOfferLine(ProductOfferLineVo productOfferLineVo) {
		productOfferLine = new ProductOfferLine();
		try {
			Product product = productService.getProduct(productOfferLineVo.getProductVo().getProductId());
			productOfferLine.setProduct(product);
			productOfferLine = productOfferLineService.setProductOfferLine(productOfferLineVo,productOfferLine);
			productOfferLineService.addProductOfferLine(productOfferLine);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return response;
		
	}
	
	@Path("/updateofferline")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel updateProductOffer(ProductOfferLineVo productOfferLineVo) {
		productOfferLine = new ProductOfferLine();
		try {
			productOfferLine = productOfferLineService.getProductOfferLine(productOfferLineVo.getProductOfferLineId());
			Product product = productService.getProduct(productOfferLineVo.getProductVo().getProductId());
			productOfferLine.setProduct(product);
			productOfferLine = productOfferLineService.setProductOfferLine(productOfferLineVo,productOfferLine);
			productOfferLineService.updateProductOfferLine(productOfferLine);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return response;
		
	}
	
	@Path("/deleteofferline")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteProductOffer(ProductOfferLineVo productOfferLineVo) {
		productOfferLine = new ProductOfferLine();
		try {
			productOfferLine = productOfferLineService.getProductOfferLine(productOfferLineVo.getProductOfferLineId());
			productOfferLineService.deleteProductOfferLine(productOfferLine);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return response;
		
	}
	
}
