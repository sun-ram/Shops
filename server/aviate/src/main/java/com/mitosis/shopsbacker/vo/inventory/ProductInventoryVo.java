package com.mitosis.shopsbacker.vo.inventory;

import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;

public class ProductInventoryVo {
	
	private String productInventoryId;
	private StoragebinVo storagebinVo;
	private MerchantVo merchantVo;
	private ProductVo product;
	private StoreVo storevo;
	private int availableQty;
	private WarehouseVo warehouse;
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductInventoryId() {
		return productInventoryId;
	}
	public void setProductInventoryId(String productInventoryId) {
		this.productInventoryId = productInventoryId;
	}
	
	public ProductVo getProduct() {
		return product;
	}
	public void setProduct(ProductVo product) {
		this.product = product;
	}
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
	public MerchantVo getMerchantVo() {
		return merchantVo;
	}
	public void setMerchantVo(MerchantVo merchantVo) {
		this.merchantVo = merchantVo;
	}
	public StoreVo getStorevo() {
		return storevo;
	}
	public void setStorevo(StoreVo storevo) {
		this.storevo = storevo;
	}
	public StoragebinVo getStoragebinVo() {
		return storagebinVo;
	}
	public void setStoragebinVo(StoragebinVo storagebinVo) {
		this.storagebinVo = storagebinVo;
	}
	public WarehouseVo getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(WarehouseVo warehouse) {
		this.warehouse = warehouse;
	}
	
}
