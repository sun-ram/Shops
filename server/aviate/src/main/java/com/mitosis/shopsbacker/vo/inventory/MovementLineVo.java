package com.mitosis.shopsbacker.vo.inventory;


public class MovementLineVo {
	private String movementLineId;
	private String movementId;
	private StoragebinVo fromStoragebin;
	private StoragebinVo toStoragebin;
	private ProductVo product;
	private int qty;

	public String getMovementLineId() {
		return movementLineId;
	}

	public void setMovementLineId(String movementLineId) {
		this.movementLineId = movementLineId;
	}

	public StoragebinVo getFromStoragebin() {
		return fromStoragebin;
	}

	public void setFromStoragebin(StoragebinVo fromStoragebin) {
		this.fromStoragebin = fromStoragebin;
	}

	public StoragebinVo getToStoragebin() {
		return toStoragebin;
	}

	public void setToStoragebin(StoragebinVo toStoragebin) {
		this.toStoragebin = toStoragebin;
	}

	public ProductVo getProduct() {
		return product;
	}

	public void setProduct(ProductVo product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getMovementId() {
		return movementId;
	}

	public void setMovementId(String movementId) {
		this.movementId = movementId;
	}

}
