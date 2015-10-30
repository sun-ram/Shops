package com.mitosis.aviate.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Table(name = "address_book")
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private Long addressId;
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="address")
	private String address;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="country")
	private String country;
	@Column(name="postal_code")
	private String postalCode;
	@Column(name="latitude")
	private String latitude;
	@Column(name="longitude")
	private String longitude;
	@Column(name="address_specific_instruction")
	private String addressSpecificInstruction;
	@Column(name="primary_address")
	private boolean primaryAddress;
	@Column(name="active")
	private boolean active;
	@Column(name="created_by")
	private Long createdBy;
	@Column(name="updated_by")
	private Long updatedBy;

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Long getAddressId() {
		return addressId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddressSpecificInstruction() {
		return addressSpecificInstruction;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAddressSpecificInstruction(String addressSpecificInstruction) {
		this.addressSpecificInstruction = addressSpecificInstruction;
	}
	public boolean isPrimaryAddress() {
		return primaryAddress;
	}
	public void setPrimaryAddress(boolean primaryAddress) {
		this.primaryAddress = primaryAddress;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
}