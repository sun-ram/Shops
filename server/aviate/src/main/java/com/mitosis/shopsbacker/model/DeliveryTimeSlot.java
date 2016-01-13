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
		"FROM_TIME", "TO_TIME", "STORE_ID" }))
public class DeliveryTimeSlot implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deliveryTimeSlotId;
	private String createdby;
	private String updatedby;
	private Merchant merchant;
	private Date fromTime;
	private Date toTime;
	private char isactive;
	private Date created;
	private Date updated;
	private Store store;
	
	public DeliveryTimeSlot() {
	}

	public DeliveryTimeSlot(String deliveryTimeSlotId, String createdby,
			String updatedby, Merchant merchant, Date fromTime,Store store,
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
		this.store=store;
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

 
	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	
	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
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
	@ManyToOne
	@JoinColumn(name = "STORE_ID")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
