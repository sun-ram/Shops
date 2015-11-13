package com.mitosis.shopsbacker.vo.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.aviate.model.StorageBinModel;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Movement;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WarehouseVo {

	private String warehouseId;
	private User userByUpdatedby;
	private User userByCreatedby;
	private Merchant merchant;
	private Store store;
	private String name;
	private String description;
	private char isactive;
	private Date created;
	private Date updated;
	private List<Movement> movements = new ArrayList<Movement>();
	private List<Storagebin> storagebins = new ArrayList<Storagebin>();
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public User getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}
	public User getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public List<Movement> getMovements() {
		return movements;
	}
	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
	public List<Storagebin> getStoragebins() {
		return storagebins;
	}
	public void setStoragebins(List<Storagebin> storagebins) {
		this.storagebins = storagebins;
	}




}
