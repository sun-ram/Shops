package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 3:32:23 PM 

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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

	private String imageId;
	private String name;
	private String type;
	private String url;
	private char isactive;
	private String createdby;
	private String updatedby;
	private Date created;
	private Date updated;
	private Set productImages = new HashSet(0);
	private Set banners = new HashSet(0);
	private Set users = new HashSet(0);
	private Set products = new HashSet(0);

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
			Date updated, Set productImages, Set banners, Set users,
			Set products) {
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
		this.banners = banners;
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
	public Set getProductImages() {
		return this.productImages;
	}

	public void setProductImages(Set productImages) {
		this.productImages = productImages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
	public Set getBanners() {
		return this.banners;
	}

	public void setBanners(Set banners) {
		this.banners = banners;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "image")
	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}
