package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * Movement Created by Sundaram C.
 */
@Entity
@Table(name = "movement")
public class Movement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String movementId;
	private String createdby;
	private String updatedby;
	private Merchant merchant;
	private Warehouse warehouse;
	private Store store;
	private String name;
	private char ismoved;
	private char isMovement;
	private char isupdated;
	private char isactive;
	private Date created;
	private Date updated;
	private List<MovementLine> movementLines = new ArrayList<MovementLine>();

	public Movement() {
	}

	public Movement(String movementId, String createdby,
			String updatedby, char ismoved, char isupdated, char isactive,
			Date created, Date updated, char isMovement) {
		this.movementId = movementId;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.ismoved = ismoved;
		this.isupdated = isupdated;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.isMovement = isMovement;
	}

	public Movement(String movementId, String createdby,
			String updatedby, Merchant merchant, Warehouse warehouse,
			Store store, String name, char ismoved, char isupdated,
			char isactive, Date created, Date updated, List<MovementLine> movementLines, char isMovement) {
		this.movementId = movementId;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.merchant = merchant;
		this.warehouse = warehouse;
		this.store = store;
		this.name = name;
		this.ismoved = ismoved;
		this.isupdated = isupdated;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.movementLines = movementLines;
		this.isMovement = isMovement;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "MOVEMENT_ID", unique = true, nullable = false, length = 32)
	public String getMovementId() {
		return this.movementId;
	}

	public void setMovementId(String movementId) {
		this.movementId = movementId;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movement", cascade=CascadeType.ALL)
	public List<MovementLine> getMovementLines() {
		return this.movementLines;
	}

	public void setMovementLines(List<MovementLine> movementLines) {
		this.movementLines = movementLines;
	}
	@Column(name = "ISMOVEMENT", nullable = false, length = 1)
	public char getIsMovement() {
		return isMovement;
	}

	public void setIsMovement(char isMovement) {
		this.isMovement = isMovement;
	}

}
