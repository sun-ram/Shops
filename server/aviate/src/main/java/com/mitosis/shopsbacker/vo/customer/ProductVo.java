package com.mitosis.shopsbacker.vo.customer;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.vo.admin.MerchantVo;

/**
 * Product Created by Sundaram C.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductVo implements java.io.Serializable {

	private String productId;
	private UserVo userByCreatedby;
	private UserVo userByUpdatedby;
	private ImageVo image;
	private MerchantVo merchant;
	private DiscountVo discount;
	private ProductCategoryVo productCategory;
	private UomVo uom;
	private ProductTypeVo productType;
	private String name;
	private String edibleType;
	private Integer groupCount;
	private String brand;
	private BigDecimal price;
	private Character isYourHot;
	private BigDecimal unit;
	private Date created;
	private Date updated;
	private char isactive;
	private List<ProductOfferLineVo> productOfferLines = new ArrayList<ProductOfferLineVo>();
	private List<MyCartVo> myCarts = new ArrayList<MyCartVo>();
	private List<SalesOrderLineVo> salesOrderLines = new ArrayList<SalesOrderLineVo>();
	private List<MovementLineVo> movementLines = new ArrayList<MovementLineVo>();
	private List<ProductImageVo> productImages = new ArrayList<ProductImageVo>();
	private List<ProductOfferVo> productOffers = new ArrayList<ProductOfferVo>();
	private List<ProductInventoryVo> productInventories = new ArrayList<ProductInventoryVo>();
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public UserVo getUserByCreatedby() {
		return userByCreatedby;
	}
	public void setUserByCreatedby(UserVo userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}
	public UserVo getUserByUpdatedby() {
		return userByUpdatedby;
	}
	public void setUserByUpdatedby(UserVo userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}
	public ImageVo getImage() {
		return image;
	}
	public void setImage(ImageVo image) {
		this.image = image;
	}
	public MerchantVo getMerchant() {
		return merchant;
	}
	public void setMerchant(MerchantVo merchant) {
		this.merchant = merchant;
	}
	public DiscountVo getDiscount() {
		return discount;
	}
	public void setDiscount(DiscountVo discount) {
		this.discount = discount;
	}
	public ProductCategoryVo getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategoryVo productCategory) {
		this.productCategory = productCategory;
	}
	public UomVo getUom() {
		return uom;
	}
	public void setUom(UomVo uom) {
		this.uom = uom;
	}
	public ProductTypeVo getProductType() {
		return productType;
	}
	public void setProductType(ProductTypeVo productType) {
		this.productType = productType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEdibleType() {
		return edibleType;
	}
	public void setEdibleType(String edibleType) {
		this.edibleType = edibleType;
	}
	public Integer getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Character getIsYourHot() {
		return isYourHot;
	}
	public void setIsYourHot(Character isYourHot) {
		this.isYourHot = isYourHot;
	}
	public BigDecimal getUnit() {
		return unit;
	}
	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public char getIsactive() {
		return isactive;
	}
	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	public List<ProductOfferLineVo> getProductOfferLines() {
		return productOfferLines;
	}
	public void setProductOfferLines(List<ProductOfferLineVo> productOfferLines) {
		this.productOfferLines = productOfferLines;
	}
	public List<MyCartVo> getMyCarts() {
		return myCarts;
	}
	public void setMyCarts(List<MyCartVo> myCarts) {
		this.myCarts = myCarts;
	}
	public List<SalesOrderLineVo> getSalesOrderLines() {
		return salesOrderLines;
	}
	public void setSalesOrderLines(List<SalesOrderLineVo> salesOrderLines) {
		this.salesOrderLines = salesOrderLines;
	}
	public List<MovementLineVo> getMovementLines() {
		return movementLines;
	}
	public void setMovementLines(List<MovementLineVo> movementLines) {
		this.movementLines = movementLines;
	}
	public List<ProductImageVo> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<ProductImageVo> productImages) {
		this.productImages = productImages;
	}
	public List<ProductOfferVo> getProductOffers() {
		return productOffers;
	}
	public void setProductOffers(List<ProductOfferVo> productOffers) {
		this.productOffers = productOffers;
	}
	public List<ProductInventoryVo> getProductInventories() {
		return productInventories;
	}
	public void setProductInventories(List<ProductInventoryVo> productInventories) {
		this.productInventories = productInventories;
	}


}
