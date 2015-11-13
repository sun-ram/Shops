package com.mitosis.shopsbacker.model;

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

/**
 * MovementLine Created by Sundaram C.
 */
@Entity
@Table(name = "movement_line", catalog = "shopsbacker")
public class MovementLine implements java.io.Serializable {

	private String movementLineId;
	private User userByUpdatedby;
	private User userByCreatedby;
	private Storagebin storagebinByFromBinId;
	private Movement movement;
	private Storagebin storagebinByToBinId;
	private Product product;
	private int qty;
	private char isactive;
	private Date created;
	private Date updated;

	public MovementLine() {
	}

	public MovementLine(String movementLineId, User userByUpdatedby,
			User userByCreatedby, Movement movement,
			Storagebin storagebinByToBinId, Product product, int qty,
			char isactive, Date created, Date updated) {
		this.movementLineId = movementLineId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.movement = movement;
		this.storagebinByToBinId = storagebinByToBinId;
		this.product = product;
		this.qty = qty;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public MovementLine(String movementLineId, User userByUpdatedby,
			User userByCreatedby, Storagebin storagebinByFromBinId,
			Movement movement, Storagebin storagebinByToBinId, Product product,
			int qty, char isactive, Date created, Date updated) {
		this.movementLineId = movementLineId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.storagebinByFromBinId = storagebinByFromBinId;
		this.movement = movement;
		this.storagebinByToBinId = storagebinByToBinId;
		this.product = product;
		this.qty = qty;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@Column(name = "MOVEMENT_LINE_ID", unique = true, nullable = false, length = 32)
	public String getMovementLineId() {
		return this.movementLineId;
	}

	public void setMovementLineId(String movementLineId) {
		this.movementLineId = movementLineId;
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
	@JoinColumn(name = "FROM_BIN_ID")
	public Storagebin getStoragebinByFromBinId() {
		return this.storagebinByFromBinId;
	}

	public void setStoragebinByFromBinId(Storagebin storagebinByFromBinId) {
		this.storagebinByFromBinId = storagebinByFromBinId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MOVEMENT_ID", nullable = false)
	public Movement getMovement() {
		return this.movement;
	}

	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TO_BIN_ID", nullable = false)
	public Storagebin getStoragebinByToBinId() {
		return this.storagebinByToBinId;
	}

	public void setStoragebinByToBinId(Storagebin storagebinByToBinId) {
		this.storagebinByToBinId = storagebinByToBinId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "QTY", nullable = false)
	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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