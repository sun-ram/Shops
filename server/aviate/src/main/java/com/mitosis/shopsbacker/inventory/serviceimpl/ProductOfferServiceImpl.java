package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.inventory.dao.ProductOfferDao;
import com.mitosis.shopsbacker.inventory.service.ProductOfferLineService;
import com.mitosis.shopsbacker.inventory.service.ProductOfferService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductOfferLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
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
	
	@Autowired
	ProductOfferLineService<T> productOfferLineService;
	
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
			productOffer = (ProductOffer) CommonUtil.setAuditColumnInfo(ProductOffer.class.getName(), null);
			productOffer.setIsactive('Y');
		}else{
			productOffer.setUpdated(new Date());
			productOffer.setUpdatedby("123");
		}
		productOffer.setName(productOfferVo.getName());
		productOffer.setOfferAmount(productOfferVo.getOfferAmount());
		productOffer.setQty(productOfferVo.getQty());
		productOffer.setDescription(productOfferVo.getDescription());
		productOffer.setFromDate(productOfferVo.getFromDate());
		productOffer.setTodate(productOfferVo.getTodate());
		productOffer.setStartTime(productOfferVo.getStartTime());
		productOffer.setEndTime(productOfferVo.getEndTime());
		return productOffer;
	} 
	
	public ProductOfferVo setProductOfferVo(ProductOffer productOffer) throws Exception{
		ProductOfferVo productOfferVo = new ProductOfferVo();
		productOfferVo.setProductOfferId(productOffer.getProductOfferId());
		productOfferVo.setName(productOffer.getName());
		productOfferVo.setOfferAmount(productOffer.getOfferAmount());
		productOfferVo.setQty(productOffer.getQty());
		productOfferVo.setDescription(productOffer.getDescription());
		productOfferVo.setFromDate(productOffer.getFromDate());
		productOfferVo.setTodate(productOffer.getTodate());
		productOfferVo.setStartTime(productOffer.getStartTime());
		productOfferVo.setEndTime(productOffer.getEndTime());
		if(productOffer.getProductOfferLines() != null){
		for(ProductOfferLine productOfferLine : productOffer.getProductOfferLines()){
			productOfferVo.getProductOfferLinesVo().add(productOfferLineService.setProductOfferLineVo(productOfferLine));
		}
		}
		Product product =productOffer.getProduct();
		ProductVo productVo=new ProductVo();
		productVo.setName(product.getName());
		productVo.setPrice(product.getPrice());
		productVo.setProductId(product.getProductId());
		productOfferVo.setProductVo(productVo);
		
		StoreVo store = new StoreVo();
		store.setStoreId(productOffer.getStore().getStoreId());
		store.setName(productOffer.getStore().getName());
		productOfferVo.setStore(store);
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

	@Override
	public List<ProductOffer> checkUniqueName(String params, Store store) {
		return productOfferDao.checkUniqueName(params,store);
	}

	@Override
	public List<ProductOffer> getProductOfferByStore(Store store) {
		return productOfferDao.getProductOfferByStore(store);
	} 

}
