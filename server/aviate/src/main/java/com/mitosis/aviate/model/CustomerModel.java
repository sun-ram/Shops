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
import javax.xml.bind.annotation.XmlRootElement;
import com.mitosis.aviate.model.service.ResponseModel;

@XmlRootElement
@Entity
@Table(name = "customer")
public class CustomerModel extends ResponseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="email_id")
	private String emailId;
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;
	@Column(name="created_by")
	private Long createdBy;
	@Column(name="updated_by")
	private Long updatedBy;

	@OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private List<Address> address;

	@OneToOne (orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id", unique= false, nullable=false, insertable=false, updatable=false,referencedColumnName="customer_id")
	private CustomerDetailsModel customerDetails;

	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public CustomerDetailsModel getCustomerDetails() {
		return customerDetails;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setCustomerDetails(CustomerDetailsModel customerDetails) {
		this.customerDetails = customerDetails;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
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
