package com.mitosis.aviate.model;
import java.util.ArrayList;
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
@Table(name = "product_details")
public class ProductDetails extends ResponseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long productId;
	@Column(name="product_type_id")
	private Long productTypeId;
	private String productName;
	private String measurement;
	private String brand;
	@Column(name="group_count")
	private String groupCount;
	@Column(name="product_offer_id")
	private Long productOfferId;
	@Column(name="store_id")
	private int storeId;
	@Column(name="merchant_id")
	private int merchantId;
	private String avilability;
	@Column(name="type")
	private String type;

	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="product_id")
	private ProductPriceDetails productPrice;

	@OneToMany(orphanRemoval=true, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")
	private List<ProductImages> productImages = new ArrayList<ProductImages>();
	
	@OneToOne (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="product_id", unique= true, nullable=true, insertable=false, updatable=false, referencedColumnName="product_id")
	private ProductUOM productUnitOfMeasure;
	
	public List<ProductImages> getProductImages() {
		return productImages;
	}
	public ProductPriceDetails getProductPrice() {
		return productPrice;
	}
	public void setProductImages(List<ProductImages> productImages) {
		this.productImages = productImages;
	}
	public void setProductPrice(ProductPriceDetails productPrice) {
		this.productPrice = productPrice;
	}
	public String getBrand() {
		return brand;
	}
	public String getGroupCount() {
		return groupCount;
	}
	public String getAvilability() {
		return avilability;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setGroupCount(String groupCount) {
		this.groupCount = groupCount;
	}
	public void setAvilability(String avilability) {
		this.avilability = avilability;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public Long getProductOfferId() {
		return productOfferId;
	}
	public void setProductOfferId(Long productOfferId) {
		this.productOfferId = productOfferId;
	}
	public ProductUOM getProductUnitOfMeasure() {
		return productUnitOfMeasure;
	}
	public void setProductUnitOfMeasure(ProductUOM productUnitOfMeasure) {
		this.productUnitOfMeasure = productUnitOfMeasure;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getMerchantId() {	
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	
}
