
package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.inventory.dao.ProductOfferLineDao;
import com.mitosis.shopsbacker.inventory.service.ProductOfferLineService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductOfferLine;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferLineVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;

@Service("productofferlineserviceimpl")
public class ProductOfferLineServiceImpl<T> implements ProductOfferLineService<T> {
	
private static final long serialVersionUID = 1L;
	
	@Autowired
	ProductOfferLineDao<T> productOfferLineDao;
	
	@Autowired
	ProductService<T> productService;
	
	public ProductOfferLineDao<T> getProductOfferLineDao() {
		return productOfferLineDao;
	}

	public void setProductOfferLineDao(ProductOfferLineDao<T> productOfferLineDao) {
		this.productOfferLineDao = productOfferLineDao;
	}
	
	public ProductService<T> getProductService() {
		return productService;
	}

	public void setProductService(ProductService<T> productService) {
		this.productService = productService;
	}



	ProductOfferLine productOfferLine = null;
	
	@Override
	public void addProductOfferLine(ProductOfferLine productOfferLine) {
		productOfferLineDao.addProductOfferLine(productOfferLine);
	}

	@Override
	public void updateProductOfferLine(ProductOfferLine productOfferLine) {
		productOfferLineDao.updateProductOfferLine(productOfferLine);
	}

	@Override
	public void deleteProductOfferLine(ProductOfferLine productOfferLine) {
		productOfferLineDao.deleteProductOfferLine(productOfferLine);
	}
	
	public ProductOfferLine setProductOfferLine(ProductOfferLineVo productOfferLineVo, ProductOfferLine productOfferLine) throws Exception{
		if(productOfferLineVo.getProductOfferLineId() == null){
			productOfferLine = (ProductOfferLine) CommonUtil.setAuditColumnInfo(ProductOfferLine.class.getName());
			productOfferLine.setIsactive('Y');
		}else{
			productOfferLine.setUpdated(new Date());
			productOfferLine.setUpdatedby("123");
		}
		productOfferLine.setDiscountAmount(productOfferLineVo.getDiscountAmount());
		productOfferLine.setDiscountPercentage(productOfferLineVo.getDiscountPercentage());
		return productOfferLine;
	} 
	
	public ProductOfferLineVo setProductOfferLineVo(ProductOfferLine productOfferLine) throws Exception{
		ProductOfferLineVo productOfferLineVo = new ProductOfferLineVo();
		productOfferLineVo.setProductOfferLineId(productOfferLine.getProductOfferLineId());
		productOfferLineVo.setDiscountAmount(productOfferLine.getDiscountAmount());
		productOfferLineVo.setDiscountPercentage(productOfferLine.getDiscountPercentage());
		Product product =productOfferLine.getProduct();
		ProductVo productVo=new ProductVo();
		productVo.setName(product.getName());
		productVo.setPrice(product.getPrice());
		productVo.setProductId(product.getProductId());
		productOfferLineVo.setProductVo(productVo);
		return productOfferLineVo;
	}

	@Override
	public ProductOfferLine getProductOfferLine(String id) {
		return productOfferLineDao.getProductOfferLine(id);
	}

	@Override
	public List<ProductOfferLine> getProductOfferLine(ProductOffer productOffer) {
		return productOfferLineDao.getProductOfferLine(productOffer);
	} 
	

}
