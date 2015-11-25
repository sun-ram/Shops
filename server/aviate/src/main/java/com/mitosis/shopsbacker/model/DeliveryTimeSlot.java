package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * DeliveryTimeSlot Created by Sundaram C.
 */
@Entity
@Table(name = "delivery_time_slot", uniqueConstraints = @UniqueConstraint(columnNames = {
		"FROM_TIME", "TO_TIME", "MERCHNAT_ID" }))
public class DeliveryTimeSlot implements java.io.Serializable {

	private String deliveryTimeSlotId;
	private String createdby;
	private String updatedby;
	private Merchant merchant;
	private Date fromTime;
	private Date toTime;
	private char isactive;
	private Date created;
	private Date updated;

	public DeliveryTimeSlot() {
	}

	public DeliveryTimeSlot(String deliveryTimeSlotId, String createdby,
			String updatedby, Merchant merchant, Date fromTime,
			Date toTime, char isactive, Date created, Date updated) {
		this.deliveryTimeSlotId = deliveryTimeSlotId;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.merchant = merchant;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "DELIVERY_TIME_SLOT_ID", unique = true, nullable = false, length = 32)
	public String getDeliveryTimeSlotId() {
		return this.deliveryTimeSlotId;
	}

	public void setDeliveryTimeSlotId(String deliveryTimeSlotId) {
		this.deliveryTimeSlotId = deliveryTimeSlotId;
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
