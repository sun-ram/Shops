package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * MovementLine Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MovementLineVo implements java.io.Serializable {

	private String movementLineId;
	private UserVo userByUpdatedby;
	private UserVo userByCreatedby;
	private StoragebinVo storagebinByFromBinId;
	private MovementVo movement;
	private StoragebinVo storagebinByToBinId;
	private ProductVo product;
	private int qty;
	private char isactive;
	private Date created;
	private Date updated;
	
	public String getMovementLineId() {
		return movementLineId;
	}
	public void setMovementLineId(String movementLineId) {
		this.movementLineId = movementLineId;
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
	public StoragebinVo getStoragebinByFromBinId() {
		return storagebinByFromBinId;
	}
	public void setStoragebinByFromBinId(StoragebinVo storagebinByFromBinId) {
		this.storagebinByFromBinId = storagebinByFromBinId;
	}
	public MovementVo getMovement() {
		return movement;
	}
	public void setMovement(MovementVo movement) {
		this.movement = movement;
	}
	public StoragebinVo getStoragebinByToBinId() {
		return storagebinByToBinId;
	}
	public void setStoragebinByToBinId(StoragebinVo storagebinByToBinId) {
		this.storagebinByToBinId = storagebinByToBinId;
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


}
