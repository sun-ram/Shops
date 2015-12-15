package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.dao.DiscountDao;
import com.mitosis.shopsbacker.inventory.service.DiscountService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.inventory.DiscountVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;

/**
 * @author RiyazKhan.M
 */
@Service("discountServiceImpl")
public class DiscountServiceImpl<T> implements DiscountService<T>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	DiscountDao<T> discountDao;
	
	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	ProductService<T> productService;

	@Override
	public void addDiscount(Discount discount) {
		discountDao.addDiscount(discount);
		
	}

	@Override
	public void updateDiscount(Discount discount) {
		discountDao.updateDiscount(discount);
		
	}

	@Override
	public void deleteDiscount(Discount discount) {
		discountDao.deleteDiscount(discount);
		
	}

	@Override
	public List<Discount> getAllDiscountByMerchant(Merchant merchant) {
		return discountDao.getAllDiscountByMerchant(merchant);
	}

	@Override
	public Discount setDiscount(DiscountVo discountVo) throws Exception {
		Discount discount = null;
		if(discountVo.getDiscountId() == null){
			discount = (Discount) CommonUtil.setAuditColumnInfo(Discount.class.getName());
			discount.setIsactive('Y');
		}else{
			discount = discountDao.getDiscountById(discountVo.getDiscountId());
		
			}
		discount.setName(discountVo.getName());
		discount.setDiscountPercentage(discountVo.getDiscountPercentage());
		discount.setDiscountAmount(discountVo.getDiscountAmount());
		discount.setMinAmount(discountVo.getMinAmount());
		discount.setMinQty(discountVo.getMinQty());
		discount.setMaxQty(discountVo.getMaxQty());
		discount.setStartDate(discountVo.getStartDate());
		discount.setEndDate(discountVo.getEndDate());
		discount.setStartTime(discountVo.getStartTime());
		discount.setEndTime(discountVo.getEndTime());
		return discount;
	}

	@Override
	public DiscountVo setDiscountVo(Discount discount) throws Exception {
		
		DiscountVo discountVo = new DiscountVo();
		
		discountVo.setDiscountId(discount.getDiscountId());
		discountVo.setName(discount.getName());
		discountVo.setDiscountPercentage(discount.getDiscountPercentage());
		discountVo.setDiscountAmount(discount.getDiscountAmount());
		discountVo.setMinAmount(discount.getMinAmount());
		discountVo.setMinQty(discount.getMinQty());
		discountVo.setMaxQty(discount.getMaxQty());
		discountVo.setStartDate(discount.getStartDate());
		discountVo.setEndDate(discount.getEndDate());
		discountVo.setStartTime(discount.getStartTime());
		discountVo.setEndTime(discount.getEndTime());
			StoreVo storevo = storeService.setStoreVo(discount.getStore());
			
		discountVo.setStore(storevo);
		List<ProductVo> productVos = new ArrayList<ProductVo>();
		for(Product product:discount.getProducts()){
			ProductVo productVo = productService.setProductVo(product) ;
			productVos.add(productVo);
		}
		discountVo.setProducts(productVos);
		return discountVo;
	}

	@Override
	public Discount getDiscountById(String discountId) {
		return discountDao.getDiscountById(discountId);
	}

	@Override
	public List<Discount> getAllDiscountByStore(Store store) {
		return discountDao.getAllDiscountByStore(store);
	}

}
