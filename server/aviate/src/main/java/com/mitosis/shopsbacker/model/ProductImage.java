package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * ProductImage Created by Sundaram C.
 */
@Entity
@Table(name = "product_image", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"PRODUCT_ID", "IMAGE_ID" }))
public class ProductImage implements java.io.Serializable {

	private String productImageId;
	private User userByCreatedby;
	private User userByUpdatedby;
	private Image image;
	private Product product;
	private String isactive;
	private Date created;
	private Date updated;

	public ProductImage() {
	}

	public ProductImage(String productImageId, User userByCreatedby,
			User userByUpdatedby, Image image, Product product,
			String isactive, Date created, Date updated) {
		this.productImageId = productImageId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.image = image;
		this.product = product;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@Column(name = "PRODUCT_IMAGE_ID", unique = true, nullable = false, length = 32)
	public String getProductImageId() {
		return this.productImageId;
	}

	public void setProductImageId(String productImageId) {
		this.productImageId = productImageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public User getUserByCreatedby() {
		return this.userByCreatedby;
	}

	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public User getUserByUpdatedby() {
		return this.userByUpdatedby;
	}

	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMAGE_ID", nullable = false)
	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 45)
	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
