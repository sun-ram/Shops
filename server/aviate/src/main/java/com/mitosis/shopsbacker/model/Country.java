package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 3:32:23 PM 

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Country Created by Sundaram C.
 */
@Entity
@Table(name = "country", catalog = "shopsbacker", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE"),
		@UniqueConstraint(columnNames = "NAME") })
public class Country implements java.io.Serializable {

	private String countryId;
	private String name;
	private String code;
	private String currencyCode;
	private String currencyName;
	private char isactive;
	private Set addresses = new HashSet(0);
	private Set states = new HashSet(0);

	public Country() {
	}

	public Country(String countryId, String name, String code, char isactive) {
		this.countryId = countryId;
		this.name = name;
		this.code = code;
		this.isactive = isactive;
	}

	public Country(String countryId, String name, String code,
			String currencyCode, String currencyName, char isactive,
			Set addresses, Set states) {
		this.countryId = countryId;
		this.name = name;
		this.code = code;
		this.currencyCode = currencyCode;
		this.currencyName = currencyName;
		this.isactive = isactive;
		this.addresses = addresses;
		this.states = states;
	}

	@Id
	@Column(name = "COUNTRY_ID", unique = true, nullable = false, length = 32)
	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	@Column(name = "NAME", unique = true, nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", unique = true, nullable = false, length = 45)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "CURRENCY_CODE", length = 45)
	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name = "CURRENCY_NAME", length = 45)
	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	public Set getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set addresses) {
		this.addresses = addresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	public Set getStates() {
		return this.states;
	}

	public void setStates(Set states) {
		this.states = states;
	}

}
