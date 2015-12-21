package com.mitosis.shopsbacker.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "discount_product")
public class DiscountProduct implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String discountProductId;
	private Discount discount;
	private Product product;
	private Store store;
	private char isactive;
	private Date created;
	private Date updated;
	private String createdby;
	private String updatedby;

	public DiscountProduct(  String discountProductId, Discount discount, Product product,Store store,char isactive,
			Date created,Date updated,String createdby, String updatedby){
		 this.discountProductId= discountProductId;
		 this.discount=  discount;
		 this.product= product;
		  this.store=store;
		  this.isactive= isactive;
		  this.created= created;
		  this.updated=  updated;
		  this.createdby= createdby;
		  this.updatedby= updatedby;
		
	}

	public DiscountProduct() {

	}

	@Id
	@GeneratedValue(generator = "system-uuid")		
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "DISCOUNT_PRODUCT_ID", unique = true, nullable = false, length = 32)
	public String getDiscountProductId() {
		return discountProductId;
	}

	public void setDiscountProductId(String discountProductId) {
		this.discountProductId = discountProductId;
	}
	
	@ManyToOne
	@JoinColumn(name = "DISCOUNT_ID")
	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@ManyToOne
	@JoinColumn(name = "STORE_ID")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}


	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

}
