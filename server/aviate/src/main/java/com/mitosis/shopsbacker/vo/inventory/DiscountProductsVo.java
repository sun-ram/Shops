package com.mitosis.shopsbacker.vo.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.StoreVo;

/**
 * @author Riyaz Khan
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DiscountProductsVo {
	
	private String discountProductId;
	private DiscountVo discount;
	private ProductVo product;
	private StoreVo store;
	public String getDiscountProductId() {
		return discountProductId;
	}
	public void setDiscountProductId(String discountProductId) {
		this.discountProductId = discountProductId;
	}
	public DiscountVo getDiscount() {
		return discount;
	}
	public void setDiscount(DiscountVo discount) {
		this.discount = discount;
	}
	public ProductVo getProduct() {
		return product;
	}
	public void setProduct(ProductVo product) {
		this.product = product;
	}
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	
	
}
