
package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.inventory.dao.ProductOfferLineDao;
import com.mitosis.shopsbacker.inventory.service.ProductOfferLineService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.UomService;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductImage;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductOfferLine;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferLineVo;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.mitosis.shopsbacker.vo.inventory.UomVo;

@Service("productofferlineserviceimpl")
public class ProductOfferLineServiceImpl<T> implements ProductOfferLineService<T> {
	
private static final long serialVersionUID = 1L;
	
	@Autowired
	ProductOfferLineDao<T> productOfferLineDao;
	
	@Autowired
	ProductService<T> productService;
	
	@Autowired
	ImageService<T> imageService;

	@Autowired
	UomService<T> uomService;
	
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
			productOfferLine = (ProductOfferLine) CommonUtil.setAuditColumnInfo(ProductOfferLine.class.getName(), productOfferLineVo.getUserId());
			productOfferLine.setIsactive('Y');
		}else{
			productOfferLine.setUpdated(new Date());
			productOfferLine.setUpdatedby(productOfferLineVo.getUserId());
		}
		productOfferLine.setDiscountAmount(productOfferLineVo.getDiscountAmount());
		productOfferLine.setDiscountPercentage(productOfferLineVo.getDiscountPercentage());
		return productOfferLine;
	} 
	
	public ProductOfferLineVo setProductOfferLineVo(ProductOfferLine productOfferLine, boolean fromProductVoSetup) throws Exception{
		ProductOfferLineVo productOfferLineVo = new ProductOfferLineVo();
		productOfferLineVo.setProductOfferLineId(productOfferLine.getProductOfferLineId());
		productOfferLineVo.setDiscountAmount(productOfferLine.getDiscountAmount());
		productOfferLineVo.setDiscountPercentage(productOfferLine.getDiscountPercentage());
		Product product =productOfferLine.getProduct();
		ProductVo productVo=new ProductVo();
		productVo.setName(product.getName());
		productVo.setPrice(product.getPrice());
		productVo.setProductId(product.getProductId());
		productVo.setUnit(product.getUnit());
		UomVo uomVo = uomService.setUomVo(product.getUom());
		productVo.setUom(uomVo);
		
		List<ProductImage> productImages = product.getProductImages();
		List<ImageVo> productImageVos = new ArrayList<ImageVo>();
		for (ProductImage productImage : productImages) {
			ImageVo image = imageService.setImageVo(productImage.getImage());
			productImageVos.add(image);
		}
		productVo.setImages(productImageVos);
		
		if(product.getImage() != null){
		ImageVo image = imageService.setImageVo(product.getImage());
		productVo.setImage(image);
		}
		productOfferLineVo.setProductVo(productVo);
		
		//It is used for applying offer while adding product to mycart 
		if(fromProductVoSetup){
		ProductOffer productOffer = productOfferLine.getProductOffer();
		ProductOfferVo productOfferVo = new ProductOfferVo();
		productOfferVo.setProductOfferId(productOffer.getProductOfferId());
		productOfferVo.setName(productOffer.getName());
		ProductVo productvo= new ProductVo();
		Product prod = productOffer.getProduct();
		productvo.setProductId(prod.getProductId());
		productvo.setName(prod.getName());
		productvo.setPrice(prod.getPrice());
		productOfferVo.setProductVo(productvo);
		List<ProductOfferLine> offerLines = productOffer.getProductOfferLines();
		
		List<ProductOfferLineVo> productOfferLineVos= new ArrayList<ProductOfferLineVo>();
		for(ProductOfferLine offerLine:offerLines){
		ProductOfferLineVo linesVo = setProductOfferLineVo(offerLine, false);
		productOfferLineVos.add(linesVo);
		}
		productOfferVo.setProductOfferLinesVo(productOfferLineVos);
		productOfferLineVo.setProductOfferVo(productOfferVo);
		}
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
