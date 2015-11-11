package com.mitosis.shopsbacker.vo.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.aviate.model.StorageBinModel;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WarehouseVo {
	private int warehouseId;
	private int merchantId;
	private int storeId;
	private boolean isactive;
	private Date created;
	private int createdby;
	private Date updated;
	private int updatedby;
	private String warehouseName;
	private String description;
	private int storageBin;	
	private String address1;
	private String address2;
	private String city;
	private String postelcode;
	private String state;
	private String country;


//	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinColumn(name="warehouse_address_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="warehouse_address_id")
//	private WarehouseAddressModel warehouseAddress;

	private List<StorageBinModel> storageBins = new ArrayList<StorageBinModel>();



	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	public int getMarchantId() {
		return merchantId;
	}
	public void setMarchantId(int marchantId) {
		this.merchantId = marchantId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
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
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStorageBin() {
		return storageBin;
	}
	public void setStorageBin(int storageBin) {
		this.storageBin = storageBin;
	}
	
//	public WarehouseAddressModel getWarehouseAddress() {
//		return warehouseAddress;
//	}
//	public void setWarehouseAddress(WarehouseAddressModel warehouseAddress) {
//		this.warehouseAddress = warehouseAddress;
//	}
	public List<StorageBinModel> getStorageBins() {
		return storageBins;
	}
	public void setStorageBins(List<StorageBinModel> storageBins) {
		this.storageBins = storageBins;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getCity() {
		return city;
	}
	public String getPostel_code() {
		return postelcode;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setPostelcode(String postelcode) {
		this.postelcode = postelcode;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	


}
