package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * ProductInventory Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductInventoryVo implements java.io.Serializable {

	private String productInventoryId;
	private UserVo userByCreatedby;
	private UserVo userByUpdatedby;
	private StoragebinVo storagebin;
	private MerchantVo merchant;
	private ProductVo product;
	private StoreVo store;
	private int availableQty;
	private char isactive;
	private Date created;
	private Date updated;
	public String getProductInventoryId() {
		return productInventoryId;
	}
	public void setProductInventoryId(String productInventoryId) {
		this.productInventoryId = productInventoryId;
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
	public StoragebinVo getStoragebin() {
		return storagebin;
	}
	public void setStoragebin(StoragebinVo storagebin) {
		this.storagebin = storagebin;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
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
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
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
