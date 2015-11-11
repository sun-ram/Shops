package com.mitosis.shopsbacker.vo.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SalesOrderLineVo {
	private Long SalesOrderLineId;
	private Long salesOrderId;
	private Long productName;
	private Long productId;
	private Long storageBinId;
	private Long quantity;
	private Long price;
	private Long imageUrl;

	//private ProductDetails productDetails;
	
	//private StorageBinModel storageBin;
	
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
	/*public ProductDetails getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}*/
	public Long getStorageBinId() {
		return storageBinId;
	}
	public void setStorageBinId(Long storageBinId) {
		this.storageBinId = storageBinId;
	}
	/*public StorageBinModel getStorageBin() {
		return storageBin;
	}
	public void setStorageBin(StorageBinModel storageBin) {
		this.storageBin = storageBin;
	}*/
	
}
