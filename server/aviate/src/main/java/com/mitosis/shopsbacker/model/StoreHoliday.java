/**
 * 
 */
package com.mitosis.shopsbacker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Entity
@Table(name = "store_holiday")
public class StoreHoliday {

	private static final long serialVersionUID = 1L;
	private String storeHolidayId;
	private String reason;
	private Merchant merchant;
	private Store store;
	private Date holidayDate;
	private char isactive;
	private Date created;
	private Date updated;
	private String updatedby;
	private String createdby;

	public StoreHoliday() {

	}

	public StoreHoliday(String storeHolidayId, String reason,
			Merchant merchant, Store store, Date holidayDate, char isactive,
			Date created, Date updated, String updatedby, String createdby) {
		this.storeHolidayId = storeHolidayId;
		this.reason = reason;
		this.merchant = merchant;
		this.store = store;
		this.holidayDate = holidayDate;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.updatedby = updatedby;
		this.createdby = createdby;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "STORE_HOLIDAY_ID", unique = true, nullable = false, length = 32)
	public String getStoreHolidayId() {
		return storeHolidayId;
	}

	public void setStoreHolidayId(String storeHolidayId) {
		this.storeHolidayId = storeHolidayId;
	}

	@Column(name = "REASON", length = 200)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@ManyToOne
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne
	@JoinColumn(name = "STORE_ID", nullable = false)
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HOLIDAY_DATE", nullable = false, length = 19)
	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

}
