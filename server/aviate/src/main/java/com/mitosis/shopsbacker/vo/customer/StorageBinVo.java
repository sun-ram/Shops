package com.mitosis.shopsbacker.vo.customer;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StorageBinVo {
	private int storageBinId;
	private int merchantId;
	private int storeId;
	private int warehouseId;
	private boolean isactive;
	private Date created;
	private int createdBy;
	private Date updated;
	private int updatedBy;
	private String storageBinName;
	private String xRow;
	private String yRow;
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
