package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Uom Created by Sundaram C.
 */
@Entity
@Table(name = "uom", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Uom implements java.io.Serializable {

	private String uomId;
	private String updatedby;
	private String createdby;
	private String name;
	private String description;
	private String isactive;
	private String created;
	private String updated;
	private List<Product> products = new ArrayList<Product>();

	public Uom() {
	}

	public Uom(String uomId, String updatedby, String createdby,
			String name, String description, String isactive, String created,
			String updated) {
		this.uomId = uomId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.name = name;
		this.description = description;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Uom(String uomId, String updatedby, String createdby,
			String name, String description, String isactive, String created,
			String updated, List<Product> products) {
		this.uomId = uomId;
		this.updatedby = updatedby;
		this.createdby = createdby;
		this.name = name;
		this.description = description;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
		this.products = products;
	}

	@Id
	@Column(name = "UOM_ID", unique = true, nullable = false, length = 32)
	public String getUomId() {
		return this.uomId;
	}

	public void setUomId(String uomId) {
		this.uomId = uomId;
	}

	
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Column(name = "NAME", unique = true, nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 45)
	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	@Column(name = "CREATED", nullable = false, length = 45)
	public String getCreated() {
		return this.created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Column(name = "UPDATED", nullable = false, length = 45)
	public String getUpdated() {
		return this.updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uom")
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
