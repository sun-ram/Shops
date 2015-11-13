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
 * Movement Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MovementVo implements java.io.Serializable {

	private String movementId;
	private UserVo userByCreatedby;
	private UserVo userByUpdatedby;
	private MerchantVo merchant;
	private WarehouseVo warehouse;
	private StoreVo store;
	private String name;
	private char ismoved;
	private char isupdated;
	private char isactive;
	private Date created;
	private Date updated;
	private List<MovementLineVo> movementLines = new ArrayList<MovementLineVo>();
	public String getMovementId() {
		return movementId;
	}
	public void setMovementId(String movementId) {
		this.movementId = movementId;
	}
	public UserVo getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(UserVo userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public UserVo getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(UserVo userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
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
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public List<MovementLineVo> getMovementLines() {
		return movementLines;
	}
	public void setMovementLines(List<MovementLineVo> movementLines) {
		this.movementLines = movementLines;
	}
	
	

}
