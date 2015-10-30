package com.mitosis.aviate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "storage_bin")
public class StorageBinModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="storage_bin_id")
	private int storageBinId;
	@Column(name="merchant_id")
	private int merchantId;
	@Column(name="store_id")
	private int storeId;
	@Column(name="warehouse_id")
	private int warehouseId;
	@Column(name="isactive")
	private boolean isactive;
	@Column(name="created")
	@Temporal(TemporalType.DATE)
	private Date created;
	@Column(name="createdby")
	private int createdBy;
	@Column(name="updated")
	@Temporal(TemporalType.DATE)
	private Date updated;
	@Column(name="updatedby")
	private int updatedBy;
	@Column(name="name")
	private String storageBinName;
	@Column(name="xrow")
	private String xRow;
	@Column(name="yrow")
	private String yRow;
	@Column(name="zrow")
	private String zRow;
	public int getStorageBinId() {
		return storageBinId;
	}
	public void setStorageBinId(int storageBinId) {
		this.storageBinId = storageBinId;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStorageBinName() {
		return storageBinName;
	}
	public void setStorageBinName(String storageBinName) {
		this.storageBinName = storageBinName;
	}
	public String getxRow() {
		return xRow;
	}
	public void setxRow(String xRow) {
		this.xRow = xRow;
	}
	public String getyRow() {
		return yRow;
	}
	public void setyRow(String yRow) {
		this.yRow = yRow;
	}
	public String getzRow() {
		return zRow;
	}
	public void setzRow(String zRow) {
		this.zRow = zRow;
	}
	
	}
