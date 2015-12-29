package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.dao.DiscountProductsDao;
import com.mitosis.shopsbacker.inventory.service.DiscountProductsService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.DiscountProduct;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.inventory.DiscountProductsVo;
import com.mitosis.shopsbacker.vo.inventory.DiscountVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;

/**
 * @author RiyazKhan.M
 */
@Service("discountProductsServiceImpl")
public class DiscountProductsServiceImpl<T> implements DiscountProductsService<T>, Serializable {


	private static final long serialVersionUID = 1L;
	
	@Autowired
	DiscountProductsDao<T> discountProductsDao;
	
	@Autowired
	ProductService<T> productService;
	
	@Autowired
	StoreService<T> storeService;
	
	@Override
	public void addDiscountProduct(DiscountProduct discountProduct) {
		discountProductsDao.addDiscountProduct(discountProduct);
		
	}

	@Override
	public void updateDiscountProduct(DiscountProduct discountProduct) {
		discountProductsDao.updateDiscountProduct(discountProduct);
		
	}

	@Override
	public void deleteDiscountProduct(DiscountProduct discountProduct) {
		discountProductsDao.deleteDiscountProduct(discountProduct);
		
	}

	@Override
	public DiscountProduct getDiscountProductById(String discountProductId) {
		return discountProductsDao.getDiscountProductById(discountProductId);
	}
	
	@SuppressWarnings("null")
	public DiscountProduct setDiscountProduct(DiscountProductsVo discountProductVo) throws Exception {
		DiscountProduct discountProduct = null;
		if(discountProductVo.getDiscountProductId() == null){
			discountProduct = (DiscountProduct) CommonUtil.setAuditColumnInfo(DiscountProduct.class.getName(), discountProductVo.getUserId());
			discountProduct.setIsactive('Y');
		}else{
			discountProduct.setUpdated(new Date());
		}
		Product product = productService.getProduct(discountProductVo.getProduct().getProductId());
		discountProduct.setProduct(product);
		
		return discountProduct;
	}

	public DiscountProductsVo setDiscountVo(DiscountProduct discountProduct) throws Exception {
		DiscountProductsVo discountProductVo = new DiscountProductsVo();
		discountProductVo.setDiscountProductId(discountProduct.getDiscountProductId());
		ProductVo product = new ProductVo();
		product.setName(discountProduct.getProduct().getName());
		product.setPrice(discountProduct.getProduct().getPrice());
		discountProductVo.setProduct(product);
		return discountProductVo;
	}

	@Override
	public List<DiscountProduct> getDiscountProductByMerchant(Merchant merchant) {
		return discountProductsDao.getDiscountProductByMerchant(merchant);
	}

	@Override
	public List<DiscountProduct> getDiscountProductByStore(Store store) {
		return discountProductsDao.getDiscountProductByStore(store);
	}

	@Override
	public List<DiscountProduct> getDiscountProductByDiscount(Discount discount) {
		return discountProductsDao.getDiscountProductByDiscount(discount);
	}

}
