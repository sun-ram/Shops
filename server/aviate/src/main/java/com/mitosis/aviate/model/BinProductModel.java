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
@Table(name = "bin_product")
public class BinProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bin_product_id")
	private int binProductId;
	@Column(name="merchant_id")
	private int merchantId;
	@Column(name="store_id")
	private int storeId;
	@Column(name="warehouse_id")
	private Long warehouseId;
	@Column(name="storage_bin_id")
	private int storageBinId;
	@Column(name="product_id")
	private Long productId;	
	@Column(name="available_quantity")
	private int availableQuantity;
	@Column(name="active")
	private boolean isactive;
	@Column(name="created")
	@Temporal(TemporalType.DATE)
	private Date created;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="updated")
	@Temporal(TemporalType.DATE)
	private Date updated;
	@Column(name="updated_by")
	private int updatedBy;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="warehouse_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="warehouse_id")
	private WarehouseModel warehouse;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="storage_bin_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="storage_bin_id")
	private StorageBinModel storageBin;

	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="product_id")
	private ProductDetails product;
	
	public WarehouseModel getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}
	public int getBinProductId() {
		return binProductId;
	}
	public int getMerchantId() {
		return merchantId;
	}
	public int getStoreId() {
		return storeId;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public int getStorageBinId() {
		return storageBinId;
	}
	public Long getProductId() {
		return productId;
	}
	public int getAvailableQuantity() {
		return availableQuantity;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public Date getCreated() {
		return created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setBinProductId(int binProductId) {
		this.binProductId = binProductId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public void setStorageBinId(int storageBinId) {
		this.storageBinId = storageBinId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public StorageBinModel getStorageBin() {
		return storageBin;
	}
	public ProductDetails getProduct() {
		return product;
	}
	public void setStorageBin(StorageBinModel storageBin) {
		this.storageBin = storageBin;
	}
	public void setProduct(ProductDetails product) {
		this.product = product;
	}
}
