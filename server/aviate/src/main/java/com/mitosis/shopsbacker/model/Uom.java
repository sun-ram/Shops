package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * Uom Created by Sundaram C.
 */
@Entity
@Table(name = "uom", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = "UOM_NAME"))
public class Uom implements java.io.Serializable {

	private String uomId;
	private String updatedby;
	private String createdby;
	private String name;
	private String description;
	private char isactive;
	private Date created;
	private Date updated;
	private List<Product> products = new ArrayList<Product>();

	public Uom() {
	}

	public Uom(String uomId, String updatedby, String createdby,
			String name, String description, char isactive, Date created,
			Date updated) {
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
			String name, String description, char isactive, Date created,
			Date updated, List<Product> products) {
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
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "UOM_ID", unique = true, nullable = false, length = 32)
	public String getUomId() {
		return this.uomId;
	}

	public void setUomId(String uomId) {
		this.uomId = uomId;
	}

	@Column(name = "UPDATEDBY", nullable = false, length = 32)
	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Column(name = "CREATEDBY", nullable = false, length = 32)
	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Column(name = "UOM_NAME", unique = true, nullable = false, length = 10)
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

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uom")
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
