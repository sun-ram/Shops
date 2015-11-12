package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Warehouse Created by Sundaram C.
 */
@Entity
@Table(name = "warehouse", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NAME", "STORE_ID" }))
public class Warehouse implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	

	public Warehouse() {
	}

	public Warehouse(String warehouseId, User userByUpdatedby,
			User userByCreatedby, Merchant merchant, Store store, String name,
			char isactive, Date created, Date updated) {
		this.warehouseId = warehouseId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.store = store;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Warehouse(String warehouseId, User userByUpdatedby,
			User userByCreatedby, Merchant merchant, Store store, String name,
			String description, char isactive, Date created, Date updated,
			 List<Movement> movements, List<Storagebin> storagebins) {
		this.warehouseId = warehouseId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.store = store;
		this.name = name;
		this.description = description;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.movements = movements;
		this.storagebins = storagebins;
	}

	@Id
	@Column(name = "WAREHOUSE_ID", unique = true, nullable = false, length = 32)
	public String getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public User getUserByUpdatedby() {
		return this.userByUpdatedby;
	}

	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public User getUserByCreatedby() {
		return this.userByCreatedby;
	}

	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID", nullable = false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
	public  List<Movement> getMovements() {
		return this.movements;
	}

	public void setMovements( List<Movement> movements) {
		this.movements = movements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
	public List<Storagebin> getStoragebins() {
		return this.storagebins;
	}

	public void setStoragebins(List<Storagebin> storagebins) {
		this.storagebins = storagebins;
	}

}
