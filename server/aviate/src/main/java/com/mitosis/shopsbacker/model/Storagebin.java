package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 3:32:23 PM 

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
 * Storagebin Created by Sundaram C.
 */
@Entity
@Table(name = "storagebin", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NAME", "ROW", "STACK", "LEVEL", "WAREHOUSE_ID" }))
public class Storagebin implements java.io.Serializable {

	private String storagebinId;
	private User userByUpdatedby;
	private User userByCreatedby;
	private Merchant merchant;
	private Warehouse warehouse;
	private Store store;
	private String name;
	private String description;
	private String row;
	private String stack;
	private String level;
	private char isactive;
	private Date created;
	private Date updated;
	private Set productInventories = new HashSet(0);

	public Storagebin() {
	}

	public Storagebin(String storagebinId, User userByUpdatedby,
			User userByCreatedby, Merchant merchant, Warehouse warehouse,
			Store store, String name, char isactive, Date created, Date updated) {
		this.storagebinId = storagebinId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.warehouse = warehouse;
		this.store = store;
		this.name = name;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Storagebin(String storagebinId, User userByUpdatedby,
			User userByCreatedby, Merchant merchant, Warehouse warehouse,
			Store store, String name, String description, String row,
			String stack, String level, char isactive, Date created,
			Date updated, Set productInventories) {
		this.storagebinId = storagebinId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.merchant = merchant;
		this.warehouse = warehouse;
		this.store = store;
		this.name = name;
		this.description = description;
		this.row = row;
		this.stack = stack;
		this.level = level;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.productInventories = productInventories;
	}

	@Id
	@Column(name = "STORAGEBIN_ID", unique = true, nullable = false, length = 32)
	public String getStoragebinId() {
		return this.storagebinId;
	}

	public void setStoragebinId(String storagebinId) {
		this.storagebinId = storagebinId;
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
	@JoinColumn(name = "WAREHOUSE_ID", nullable = false)
	public Warehouse getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID", nullable = false)
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "NAME", nullable = false, length = 60)
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

	@Column(name = "ROW", length = 10)
	public String getRow() {
		return this.row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	@Column(name = "STACK", length = 10)
	public String getStack() {
		return this.stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	@Column(name = "LEVEL", length = 10)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "storagebin")
	public Set getProductInventories() {
		return this.productInventories;
	}

	public void setProductInventories(Set productInventories) {
		this.productInventories = productInventories;
	}

}
