package com.mitosis.shopsbacker.vo.inventory;

public class ProductStockVo {
	private String productInventoryId;
	private int availableQty;
	private String productName;
	private String productId;
	private String storagebinName;
	private String row;
	private String stack;
	private String level;
	private String warehouseName;
	public String getProductInventoryId() {
		return productInventoryId;
	}
	public void setProductInventoryId(String productInventoryId) {
		this.productInventoryId = productInventoryId;
	}
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getStoragebinName() {
		return storagebinName;
	}
	public void setStoragebinName(String storagebinName) {
		this.storagebinName = storagebinName;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
}
