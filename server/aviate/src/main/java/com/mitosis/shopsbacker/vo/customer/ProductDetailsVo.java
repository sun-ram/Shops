package com.mitosis.shopsbacker.vo.customer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductDetailsVo {
	private Long productId;
	private Long productTypeId;
	private String productName;
	private String measurement;
	private String brand;
	private String groupCount;
	private Long productOfferId;
	private int storeId;
	private int merchantId;
	private String avilability;
	private String type;

	//private ProductPriceDetails productPrice;

	//private List<ProductImages> productImages = new ArrayList<ProductImages>();
	
	//private ProductUOM productUnitOfMeasure;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(String groupCount) {
		this.groupCount = groupCount;
	}

	public Long getProductOfferId() {
		return productOfferId;
	}

	public void setProductOfferId(Long productOfferId) {
		this.productOfferId = productOfferId;
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

	public String getAvilability() {
		return avilability;
	}

	public void setAvilability(String avilability) {
		this.avilability = avilability;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*public ProductPriceDetails getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductPriceDetails productPrice) {
		this.productPrice = productPrice;
	}*/

	/*public List<ProductImages> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImages> productImages) {
		this.productImages = productImages;
	}

	public ProductUOM getProductUnitOfMeasure() {
		return productUnitOfMeasure;
	}

	public void setProductUnitOfMeasure(ProductUOM productUnitOfMeasure) {
		this.productUnitOfMeasure = productUnitOfMeasure;
	}*/
	
	

	
}
