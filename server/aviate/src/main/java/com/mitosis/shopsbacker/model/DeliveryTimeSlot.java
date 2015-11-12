package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

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

/**
 * DeliveryTimeSlot Created by Sundaram C.
 */
@Entity
@Table(name = "delivery_time_slot", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"FROM_TIME", "TO_TIME", "MERCHNAT_ID" }))
public class DeliveryTimeSlot implements java.io.Serializable {

	private String deliveryTimeSlotId;
	private User userByCreatedby;
	private User userByUpdatedby;
	private Merchant merchant;
	private Date fromTime;
	private Date toTime;
	private char isactive;
	private Date created;
	private Date updated;

	public DeliveryTimeSlot() {
	}

	public DeliveryTimeSlot(String deliveryTimeSlotId, User userByCreatedby,
			User userByUpdatedby, Merchant merchant, Date fromTime,
			Date toTime, char isactive, Date created, Date updated) {
		this.deliveryTimeSlotId = deliveryTimeSlotId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.merchant = merchant;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@Column(name = "DELIVERY_TIME_SLOT_ID", unique = true, nullable = false, length = 32)
	public String getDeliveryTimeSlotId() {
		return this.deliveryTimeSlotId;
	}

	public void setDeliveryTimeSlotId(String deliveryTimeSlotId) {
		this.deliveryTimeSlotId = deliveryTimeSlotId;
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
	@JoinColumn(name = "MERCHNAT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "FROM_TIME", nullable = false, length = 8)
	public Date getFromTime() {
		return this.fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "TO_TIME", nullable = false, length = 8)
	public Date getToTime() {
		return this.toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
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
