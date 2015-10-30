package com.mitosis.aviate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name = "merchant")
public class MerchantDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="merchant_id")
	private int merchantId;
	@Column(name="merchant_name")
	private String merchantName;
	@Column(name="user_name")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="phone_number")
	private Long phoneNo;
	@Column(name="address")
	private String address;
	@Column(name="address_1")
	private String address1;
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
	@Column(name="longtitude")
	private String longtitude;
	@Column(name="active")
	private int active;
	@Column(name="created")
	@Temporal(TemporalType.DATE)
	private Date created;
	@Column(name="created_by")
	private int createdBy;
	@Column(name="updated")
	@Temporal(TemporalType.DATE)
	private Date updated;
	@Column(name="updated_by")
	private int updatedBy;
	/*@Column(name="image_id")
	private int image_id;*/
	
	@Transient
	private String merchantImage;
	
	@Transient
	private String merchantImageType;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="image_id", unique= true, nullable=true, insertable=true, updatable=true, referencedColumnName="image_id")
	private ProductImages productImages;
	

	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public int getCreatedBy() {
		return createdBy;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreated() {
		return created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	/*public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}*/
	
	public String getMerchantImage() {
		return merchantImage;
	}
	public void setMerchantImage(String merchantImage) {
		this.merchantImage = merchantImage;
	}
	public String getMerchantImageType() {
		return merchantImageType;
	}
	public void setMerchantImageType(String merchantImageType) {
		this.merchantImageType = merchantImageType;
	}
	public ProductImages getProductImages() {
		return productImages;
	}
	public void setProductImages(ProductImages productImages) {
		this.productImages = productImages;
	}
	
	
	
}
