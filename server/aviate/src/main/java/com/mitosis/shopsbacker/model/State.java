package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 3:32:23 PM 

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

/**
 * State Created by Sundaram C.
 */
@Entity
@Table(name = "state", catalog = "shopsbacker")
public class State implements java.io.Serializable {

	private String stateId;
	private Country country;
	private String name;
	private char isactive;
	private Set addresses = new HashSet(0);

	public State() {
	}

	public State(String stateId, Country country, String name, char isactive) {
		this.stateId = stateId;
		this.country = country;
		this.name = name;
		this.isactive = isactive;
	}

	public State(String stateId, Country country, String name, char isactive,
			Set addresses) {
		this.stateId = stateId;
		this.country = country;
		this.name = name;
		this.isactive = isactive;
		this.addresses = addresses;
	}

	@Id
	@Column(name = "STATE_ID", unique = true, nullable = false, length = 32)
	public String getStateId() {
		return this.stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "NAME", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
	public Set getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set addresses) {
		this.addresses = addresses;
	}

}
