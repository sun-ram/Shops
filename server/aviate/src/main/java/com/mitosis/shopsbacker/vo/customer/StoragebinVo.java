package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * Storagebin Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StoragebinVo implements java.io.Serializable {

	private String storagebinId;
	private UserVo userByUpdatedby;
	private UserVo userByCreatedby;
	private MerchantVo merchant;
	private WarehouseVo warehouse;
	private StoreVo store;
	public String getStoragebinId() {
		return storagebinId;
	}
	public void setStoragebinId(String storagebinId) {
		this.storagebinId = storagebinId;
	}
	public UserVo getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(UserVo userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}
	public UserVo getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(UserVo userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public WarehouseVo getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(WarehouseVo warehouse) {
		this.warehouse = warehouse;
	}
	public StoreVo getStore() {
		return store;
	}
	public void setStore(StoreVo store) {
		this.store = store;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public List<ProductInventoryVo> getProductInventories() {
		return productInventories;
	}
	public void setProductInventories(List<ProductInventoryVo> productInventories) {
		this.productInventories = productInventories;
	}
	public List<MovementLineVo> getMovementLinesForToBinId() {
		return movementLinesForToBinId;
	}
	public void setMovementLinesForToBinId(
			List<MovementLineVo> movementLinesForToBinId) {
		this.movementLinesForToBinId = movementLinesForToBinId;
	}
	public List<MovementLineVo> getMovementLinesForFromBinId() {
		return movementLinesForFromBinId;
	}
	public void setMovementLinesForFromBinId(
			List<MovementLineVo> movementLinesForFromBinId) {
		this.movementLinesForFromBinId = movementLinesForFromBinId;
	}
	private String name;
	private String description;
	private String row;
	private String stack;
	private String level;
	private char isactive;
	private Date created;
	private Date updated;
	private List<ProductInventoryVo> productInventories = new ArrayList<ProductInventoryVo>();
	private List<MovementLineVo> movementLinesForToBinId = new ArrayList<MovementLineVo>();
	private List<MovementLineVo> movementLinesForFromBinId = new ArrayList<MovementLineVo>();


}
