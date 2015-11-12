package com.mitosis.shopsbacker.model;

// Generated Nov 6, 2015 7:27:52 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Image Created by Sundaram C.
 */
@Entity
@Table(name = "image", catalog = "shopsbacker")
public class Image implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imageId;
	private String name;
	private String type;
	private String url;
	private char isactive;
	private String createdby;
	private String updatedby;
	private Date created;
	private Date updated;
	
	
	private List<ProductImage> productImages = new ArrayList<ProductImage>();
	private List<User> users = new ArrayList<User>();
	private List<Product> products = new ArrayList<Product>();
	

	public Image() {
	}

	public Image(String imageId, String name, String type, String url,
			char isactive, Date created, Date updated) {
		this.imageId = imageId;
		this.name = name;
		this.type = type;
		this.url = url;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Image(String imageId, String name, String type, String url,
			char isactive, String createdby, String updatedby, Date created,
			Date updated, List<ProductImage> productImages, List<User> users, List<Product> products) {
		this.imageId = imageId;
		this.name = name;
		this.type = type;
		this.url = url;
		this.isactive = isactive;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.created = created;
		this.updated = updated;
		this.productImages = productImages;
		this.users = users;
		this.products = products;
	}

	@Id
	@Column(name = "IMAGE_ID", unique = true, nullable = false, length = 32)
	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE", nullable = false, length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "URL", nullable = false, length = 45)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
	public List<ProductImage> getProductImages() {
		return this.productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
