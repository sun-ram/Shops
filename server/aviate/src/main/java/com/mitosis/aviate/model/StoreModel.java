package com.mitosis.aviate.model;

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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "store")
public class StoreModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="store_id")
	private Long storeId;
	@Column(name="merchant_id")
	private Long merchantId;
	public MerchantDetails getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantDetails merchant) {
		this.merchant = merchant;
	}
	@Column(name="name")
	private String storeName;
	@Column(name="store_order")
	private String storeOrder;
	@Column(name="address")
	private String address;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="latitude")
	private String latitude;
	@Column(name="longitude")
	private String longitude;
	@Column(name="postal_code")
	private Long postalCode;
	@Column(name="country")
	private String country;
	@Column(name="area")
	private String area;
	@Column(name="phone_no")
	private Long phoneNo;
	@Column(name="created_by")
	private Long createdBy;
	@Column(name="updated_by")
	private Long updatedBy;
	@Column(name="logo_url")
	private String logoUrl;
	@Column(name="image")
	private byte[] image;
	@Column(name="image_type")
	private String imageType;
	@Column(name="image_name")
	private String imageName;
	
	@Transient
	private String emailId;
	
	@Transient
	private String password;
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="merchant_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="merchant_id")
	private MerchantDetails merchant;
	
	@OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="store_id")
	private List<CustomerDetailsModel> customerDetails;
	
	public Long getStoreId() {
		return storeId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public String getStoreName() {
		return storeName;
	}
	public String getStoreOrder() {
		return storeOrder;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public List<CustomerDetailsModel> getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(List<CustomerDetailsModel> customerDetails) {
		this.customerDetails = customerDetails;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public Long getPostalCode() {
		return postalCode;
	}
	public String getCountry() {
		return country;
	}
	public String getArea() {
		return area;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setStoreOrder(String storeOrder) {
		this.storeOrder = storeOrder;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	public byte[] getImage() {
		return image;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
