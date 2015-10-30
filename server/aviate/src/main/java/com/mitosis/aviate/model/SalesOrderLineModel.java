package com.mitosis.aviate.model;

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
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "sales_order_line")
public class SalesOrderLineModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sales_order_line_id")
	private Long SalesOrderLineId;
	@Column(name="sales_order_id")
	private Long salesOrderId;
	@Column(name="product_name")
	private Long productName;
	@Column(name="product_id")
	private Long productId;
	@Column(name="storage_bin_id")
	private Long storageBinId;
	@Column(name="quantity")
	private Long quantity;
	@Column(name="product_price")
	private Long price;
	@Column(name="image_url")
	private Long imageUrl;

	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="product_id")
	private ProductDetails productDetails;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="storage_bin_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="storage_bin_id")
	private StorageBinModel storageBin;
	
	public Long getSalesOrderLineId() {
		return SalesOrderLineId;
	}
	public Long getSalesOrderId() {
		return salesOrderId;
	}
	public Long getProductName() {
		return productName;
	}
	public Long getQuantity() {
		return quantity;
	}
	public Long getImageUrl() {
		return imageUrl;
	}
	public void setSalesOrderLineId(Long salesOrderLineId) {
		SalesOrderLineId = salesOrderLineId;
	}
	public void setSalesOrderId(Long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public void setProductName(Long productName) {
		this.productName = productName;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public void setImageUrl(Long imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public ProductDetails getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	public Long getStorageBinId() {
		return storageBinId;
	}
	public void setStorageBinId(Long storageBinId) {
		this.storageBinId = storageBinId;
	}
	public StorageBinModel getStorageBin() {
		return storageBin;
	}
	public void setStorageBin(StorageBinModel storageBin) {
		this.storageBin = storageBin;
	}
	
}
