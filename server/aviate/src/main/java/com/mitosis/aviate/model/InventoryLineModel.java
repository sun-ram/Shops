package com.mitosis.aviate.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "inventory_line")
public class InventoryLineModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="inventory_line_id")
	private int inventoryLineId;
	@Column(name="merchant_id")
	private int merchantId;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="isactive")
	private boolean isactive;
	@Column(name="created")
	@Temporal(TemporalType.DATE)
	private Date created;
	@Column(name="createdby")
	private Long createdBy;
	@Column(name="updated")
	@Temporal(TemporalType.DATE)
	private Date updated;
	@Column(name="updatedby")
	private Long updatedBy;
	@Column(name="inventory_id")
	private int inventoryId;
	@Column(name="storage_bin_id")
	private int storageBinId;
	@Column(name="product_id")
	private Long productId;
	@Column(name="booked_qty")
	private int bookedQty;
	@Column(name="available_qty")
	private int availableQty;
	@Column(name="quantity")
	private int quantity;
	@Column(name="uom_id")
	private int uomId;
	@Column(name="description")
	private String description;
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="product_id")
	private ProductDetails productDetails;
	
	public ProductDetails getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	public int getInventoryLineId() {
		return inventoryLineId;
	}
	public void setInventoryLineId(int inventoryLineId) {
		this.inventoryLineId = inventoryLineId;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
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
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getStorageBinId() {
		return storageBinId;
	}
	public void setStorageBinId(int storageBinId) {
		this.storageBinId = storageBinId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getBookedQty() {
		return bookedQty;
	}
	public void setBookedQty(int bookedQty) {
		this.bookedQty = bookedQty;
	}
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getUomId() {
		return uomId;
	}
	public void setUomId(int uomId) {
		this.uomId = uomId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	//getter and setter
	

}
