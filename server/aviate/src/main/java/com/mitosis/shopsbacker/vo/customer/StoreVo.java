package com.mitosis.shopsbacker.vo.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StoreVo {
	private Long storeId;
	private Long merchantId;
	private String storeName;
	private String storeOrder;
	private String address;
	private String city;
	private String state;
	private String latitude;
	private String longitude;
	private Long postalCode;
	private String country;
	private String area;
	private Long phoneNo;
	private Long createdBy;
	private Long updatedBy;
	private String logoUrl;
	private byte[] image;
	private String imageType;
	private String imageName;
	
	private String emailId;
	
	private String password;
	
	//private MerchantDetails merchant;
	
	//private List<CustomerDetailsModel> customerDetails;
	
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
/*	public List<CustomerDetailsModel> getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(List<CustomerDetailsModel> customerDetails) {
		this.customerDetails = customerDetails;
	}*/
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
