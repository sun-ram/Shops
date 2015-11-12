package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.util.HashSet;
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
	private User userByUpdatedby;
	private User userByCreatedby;
	private String name;
	private String description;
	private String isactive;
	private String created;
	private String updated;
	private Set products = new HashSet(0);

	public Uom() {
	}

	public Uom(String uomId, User userByUpdatedby, User userByCreatedby,
			String name, String description, String isactive, String created,
			String updated) {
		this.uomId = uomId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
		this.name = name;
		this.description = description;
		this.isactive = isactive;
		this.created = created;
		this.updated = updated;
	}

	public Uom(String uomId, User userByUpdatedby, User userByCreatedby,
			String name, String description, String isactive, String created,
			String updated, Set products) {
		this.uomId = uomId;
		this.userByUpdatedby = userByUpdatedby;
		this.userByCreatedby = userByCreatedby;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public User getUserByUpdatedby() {
		return this.userByUpdatedby;
	}

	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public User getUserByCreatedby() {
		return this.userByCreatedby;
	}

	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
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
	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}
