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
 * Favourite Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class FavouriteVo implements java.io.Serializable {

	private String favouriteId;
	private MerchantVo merchant;
	private CustomerVo customerByCreatedby;
	private SalesOrderVo salesOrder;
	private CustomerVo customerByCustomerId;
	private CustomerVo customerByUpdatedby;
	private StoreVo store;
	private String name;
	private char isactive;
	private Date created;
	private Date updated;
	public String getFavouriteId() {
		return favouriteId;
	}
	public void setFavouriteId(String favouriteId) {
		this.favouriteId = favouriteId;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public CustomerVo getCustomerByCreatedby() {
		return customerByCreatedby;
	}
	public void setCustomerByCreatedby(CustomerVo customerByCreatedby) {
		this.customerByCreatedby = customerByCreatedby;
	}
	public SalesOrderVo getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(SalesOrderVo salesOrder) {
		this.salesOrder = salesOrder;
	}
	public CustomerVo getCustomerByCustomerId() {
		return customerByCustomerId;
	}
	public void setCustomerByCustomerId(CustomerVo customerByCustomerId) {
		this.customerByCustomerId = customerByCustomerId;
	}
	public CustomerVo getCustomerByUpdatedby() {
		return customerByUpdatedby;
	}
	public void setCustomerByUpdatedby(CustomerVo customerByUpdatedby) {
		this.customerByUpdatedby = customerByUpdatedby;
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
