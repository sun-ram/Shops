package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.inventory.dao.ProductOfferDao;
import com.mitosis.shopsbacker.inventory.service.ProductOfferService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferVo;
/**
 * @author RiyazKhan.M
 */

@Service("productofferserviceimpl")
public class ProductOfferServiceImpl<T> implements ProductOfferService<T>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	ProductOfferDao<T> productOfferDao;
	
	@Autowired
	ProductService<T> productService;
	
	ProductOffer productOffer = null;
	
	@Override
	public void addProductOffer(ProductOffer productOffer) {
		productOfferDao.addProductOffer(productOffer);
		
	}

	@Override
	public void updateProductOffer(ProductOffer productOffer) {
		productOfferDao.updateProductOffer(productOffer);
		
	}

	@Override
	public void deleteProductOffer(ProductOffer productOffer) {
		productOfferDao.deleteProductOffer(productOffer);
		
	}

	@Override
	public List<ProductOffer> getAllProductOffer() {
		return productOfferDao.getAllProductOffer();
	}
	
	public ProductOffer setProductOffer(ProductOfferVo productOfferVo, ProductOffer productOffer) throws Exception{
		if(productOfferVo.getProductOfferId() == null){
			productOffer = (ProductOffer) CommonUtil.setAuditColumnInfo(ProductOffer.class.getName());
			productOffer.setIsactive('Y');
		}else{
			productOffer.setUpdated(new Date());
			productOffer.setUpdatedby("123");
		}
		productOffer.setName(productOfferVo.getName());
		productOffer.setOfferAmount(productOfferVo.getOfferAmount());
		productOffer.setQty(productOfferVo.getQty());
		productOffer.setDescription(productOfferVo.getDescription());
		productOffer.setFromDate(CommonUtil.stringToDate(productOfferVo.getFromDate()));
		productOffer.setTodate(CommonUtil.stringToDate(productOfferVo.getTodate()));
		productOffer.setStartTime(productOfferVo.getStartTime());
		productOffer.setEndTime(productOfferVo.getEndTime());
		return productOffer;
	} 
	
	public ProductOfferVo setProductOfferVo(ProductOffer productOffer) throws Exception{
		ProductOfferVo productOfferVo = new ProductOfferVo();
		productOfferVo.setName(productOffer.getName());
		productOfferVo.setOfferAmount(productOffer.getOfferAmount());
		productOfferVo.setQty(productOffer.getQty());
		productOfferVo.setDescription(productOffer.getDescription());
		productOfferVo.setFromDate(CommonUtil.dateToString(productOffer.getFromDate()));
		productOfferVo.setTodate(CommonUtil.dateToString(productOffer.getTodate()));
		productOfferVo.setStartTime(productOffer.getStartTime());
		productOfferVo.setEndTime(productOffer.getEndTime());
		productOfferVo.setProductVo(productService.setProductVo(productOffer.getProduct()));
		return productOfferVo;
	}

	@Override
	public ProductOffer getProductOffer(String id) {
		return productOfferDao.getProductOffer(id);
	}

	@Override
	public List<ProductOffer> getProductOfferByMerchant(Merchant merchant) {
		return productOfferDao.getProductOfferByMerchant(merchant);
	} 

}
