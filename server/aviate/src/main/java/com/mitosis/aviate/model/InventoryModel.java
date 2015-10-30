package com.mitosis.aviate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "inventory")
public class InventoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="inventory_id")
	private int inventoryId;
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
	@Column(name="name")
	private String inventoryName;
	@Column(name="description")
	private String description;
	@Column(name="warehouse_id")
	private int warehouseId;
	@Column(name="isprocessed")
	private boolean isProcessed;
	@Column(name="isqtyupdated")
	private boolean isQtyUpdated;

	@OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="inventory_id")
	private List<InventoryLineModel> inventoryLines = new ArrayList<InventoryLineModel>();

	/*	@OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="store_id")
	private List<StoreModel> storeList = new ArrayList<StoreModel>();*/

	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="store_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="store_id")
	private StoreModel store;


	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="warehouse_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="warehouse_id")
	private WarehouseModel warehouse;

	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="merchant_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="merchant_id")
	private MerchantDetails merchant;
	
	/*	@OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="warehouse_id")
	private List<WarehouseModel> warehouseList = new ArrayList<WarehouseModel>();*/

	public int getInventoryId() {
		return inventoryId;
	}
	public MerchantDetails getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantDetails merchant) {
		this.merchant = merchant;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
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
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public boolean isProcessed() {
		return isProcessed;
	}
	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	public boolean isQtyUpdated() {
		return isQtyUpdated;
	}
	public void setQtyUpdated(boolean isQtyUpdated) {
		this.isQtyUpdated = isQtyUpdated;
	}
	public List<InventoryLineModel> getInventoryLines() {
		return inventoryLines;
	}
	public void setInventoryLines(List<InventoryLineModel> inventoryLines) {
		this.inventoryLines = inventoryLines;
	}
	public StoreModel getStore() {
		return store;
	}
	public WarehouseModel getWarehouse() {
		return warehouse;
	}
	public void setStore(StoreModel store) {
		this.store = store;
	}
	public void setWarehouse(WarehouseModel warehouse) {
		this.warehouse = warehouse;
	}

}
