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
@Table(name = "my_cart")
public class MyCartModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="my_cart_id")
	private int myCartId;
	@Column(name="customer_id")
	private int customerId;
	@Column(name="product_id")
	private int productId;
	@Column(name="quantity")
	private int quantity;
	@Column(name="price")
	private double price;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="active")
	private boolean active;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="product_id")
	private ProductDetails product;

	public int getMyCartId() {
		return myCartId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public int getProductId() {
		return productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setMyCartId(int myCartId) {
		this.myCartId = myCartId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ProductDetails getProduct() {
		return product;
	}
	public void setProduct(ProductDetails product) {
		this.product = product;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
}
