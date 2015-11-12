package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 3:32:23 PM 

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

/**
 * Movement Created by Sundaram C.
 */
@Entity
@Table(name = "movement", catalog = "shopsbacker")
public class Movement implements java.io.Serializable {

	private String movementId;
	private User userByCreatedby;
	private User userByUpdatedby;
	private Merchant merchant;
	private Warehouse warehouse;
	private Store store;
	private String name;
	private char ismoved;
	private char isupdated;
	private char isactive;
	private Date created;
	private Date updated;

	public Movement() {
	}

	public Movement(String movementId, User userByCreatedby,
			User userByUpdatedby, char ismoved, char isupdated, char isactive,
			Date created, Date updated) {
		this.movementId = movementId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.ismoved = ismoved;
		this.isupdated = isupdated;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Movement(String movementId, User userByCreatedby,
			User userByUpdatedby, Merchant merchant, Warehouse warehouse,
			Store store, String name, char ismoved, char isupdated,
			char isactive, Date created, Date updated) {
		this.movementId = movementId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.merchant = merchant;
		this.warehouse = warehouse;
		this.store = store;
		this.name = name;
		this.ismoved = ismoved;
		this.isupdated = isupdated;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@Column(name = "MOVEMENT_ID", unique = true, nullable = false, length = 32)
	public String getMovementId() {
		return this.movementId;
	}

	public void setMovementId(String movementId) {
		this.movementId = movementId;
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
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public User getUserByUpdatedby() {
		return this.userByUpdatedby;
	}

	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WAREHOUSE_ID")
	public Warehouse getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STORE_ID")
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ISMOVED", nullable = false, length = 1)
	public char getIsmoved() {
		return this.ismoved;
	}

	public void setIsmoved(char ismoved) {
		this.ismoved = ismoved;
	}

	@Column(name = "ISUPDATED", nullable = false, length = 1)
	public char getIsupdated() {
		return this.isupdated;
	}

	public void setIsupdated(char isupdated) {
		this.isupdated = isupdated;
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

}
