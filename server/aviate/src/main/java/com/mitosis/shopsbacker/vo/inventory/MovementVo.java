package com.mitosis.shopsbacker.vo.inventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Warehouse;

public class MovementVo {
	private String movementId;
	private Merchant merchant;
	private Warehouse warehouse;
	private Store store;
	private String name;
	private char ismoved;
	private char isupdated;
	private char isactive;
	private Date created;
	private List<MovementLineVo> movementLines = new ArrayList<MovementLineVo>();
	
	public String getMovementId() {
		return movementId;
	}
	public void setMovementId(String movementId) {
		this.movementId = movementId;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getIsmoved() {
		return ismoved;
	}
	public void setIsmoved(char ismoved) {
		this.ismoved = ismoved;
	}
	public char getIsupdated() {
		return isupdated;
	}
	public void setIsupdated(char isupdated) {
		this.isupdated = isupdated;
	}
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public List<MovementLineVo> getMovementLines() {
		return movementLines;
	}
	public void setMovementLines(List<MovementLineVo> movementLines) {
		this.movementLines = movementLines;
	}
	

}
