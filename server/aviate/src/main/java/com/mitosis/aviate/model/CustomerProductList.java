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
import com.mitosis.aviate.model.service.ResponseModel;

@XmlRootElement
@Entity
@Table(name = "customer_product_list")
public class CustomerProductList extends ResponseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="my_list_id")
	private Long myListId;
	@Column(name="product_id")
	private Long productId;
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="active")
	private boolean active;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id", unique= true, nullable=true, insertable=false, updatable=false /*, referencedColumnName="product_id"*/)
	private ProductDetails products;
	
	public ProductDetails getProducts() {
		return products;
	}
	public void setProducts(ProductDetails products) {
		this.products = products;
	}
	public Long getMyListId() {
		return myListId;
	}
	public Long getProductId() {
		return productId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setMyListId(Long myListId) {
		this.myListId = myListId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
