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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.aviate.model.service.ResponseModel;


@XmlRootElement
@Entity
@Table(name = "customer_details")
public class CustomerDetailsModel extends ResponseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_details_id")
	private Long customerDetailsId;
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="email_id")
	private String emailId;
	@Column(name="phone_no")
	private String phoneNo;
	@Column(name="store_id")
	private Long storeId;
	@Column(name="device_id")
	private String deviceId;
	@Column(name="device_type")
	private String deviceType;
	@Column(name="created_by")
	private Long createdBy;
	@Column(name="updated_by")
	private Long updatedBy;
	
	@Column(name="merchant_id")
	private Long merchantId;
	
	
	public Long getCustomerDetailsId() {
		return customerDetailsId;
	}
	public void setCustomerDetailsId(Long customerDetailsId) {
		this.customerDetailsId = customerDetailsId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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
