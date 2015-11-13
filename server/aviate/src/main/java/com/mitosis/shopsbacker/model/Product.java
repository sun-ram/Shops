package com.mitosis.shopsbacker.model;

// Generated Nov 12, 2015 6:16:19 PM 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Product Created by Sundaram C.
 */
@Entity
@Table(name = "product", catalog = "shopsbacker", uniqueConstraints = @UniqueConstraint(columnNames = {
		"NAME", "MERCHANT_ID", "UOM_ID", "UNIT" }))
public class Product implements java.io.Serializable {

	private String productId;
	private User userByCreatedby;
	private User userByUpdatedby;
	private Image image;
	private Merchant merchant;
	private Discount discount;
	private ProductCategory productCategory;
	private Uom uom;
	private ProductType productType;
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
	private List<ProductOfferLine> productOfferLines = new ArrayList<ProductOfferLine>();
	private List<MyCart> myCarts = new ArrayList<MyCart>();
	private List<SalesOrderLine> salesOrderLines = new ArrayList<SalesOrderLine>();
	private List<MovementLine> movementLines = new ArrayList<MovementLine>();
	private List<ProductImage> productImages = new ArrayList<ProductImage>();
	private List<ProductOffer> productOffers = new ArrayList<ProductOffer>();
	private List<ProductInventory> productInventories = new ArrayList<ProductInventory>();

	public Product() {
	}

	public Product(String productId, User userByCreatedby,
			User userByUpdatedby, Merchant merchant,
			ProductCategory productCategory, Uom uom, ProductType productType,
			String name, BigDecimal price, BigDecimal unit, Date created,
			Date updated, char isactive) {
		this.productId = productId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.merchant = merchant;
		this.productCategory = productCategory;
		this.uom = uom;
		this.productType = productType;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.created = created;
		this.updated = updated;
		this.isactive = isactive;
	}

	public Product(String productId, User userByCreatedby,
			User userByUpdatedby, Image image, Merchant merchant,
			Discount discount, ProductCategory productCategory, Uom uom,
			ProductType productType, String name, String edibleType,
			Integer groupCount, String brand, BigDecimal price,
			Character isYourHot, BigDecimal unit, Date created, Date updated,
			char isactive, List<ProductOfferLine> productOfferLines, List<MyCart> myCarts,
			List<SalesOrderLine> salesOrderLines, List<MovementLine> movementLines, List<ProductImage> productImages,
			List<ProductOffer> productOffers, List<ProductInventory> productInventories) {
		this.productId = productId;
		this.userByCreatedby = userByCreatedby;
		this.userByUpdatedby = userByUpdatedby;
		this.image = image;
		this.merchant = merchant;
		this.discount = discount;
		this.productCategory = productCategory;
		this.uom = uom;
		this.productType = productType;
		this.name = name;
		this.edibleType = edibleType;
		this.groupCount = groupCount;
		this.brand = brand;
		this.price = price;
		this.isYourHot = isYourHot;
		this.unit = unit;
		this.created = created;
		this.updated = updated;
		this.isactive = isactive;
		this.productOfferLines = productOfferLines;
		this.myCarts = myCarts;
		this.salesOrderLines = salesOrderLines;
		this.movementLines = movementLines;
		this.productImages = productImages;
		this.productOffers = productOffers;
		this.productInventories = productInventories;
	}

	@Id
	@Column(name = "PRODUCT_ID", unique = true, nullable = false, length = 32)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", nullable = false)
	public User getUserByCreatedby() {
		return this.userByCreatedby;
	}

	public void setUserByCreatedby(User userByCreatedby) {
		this.userByCreatedby = userByCreatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATEDBY", nullable = false)
	public User getUserByUpdatedby() {
		return this.userByUpdatedby;
	}

	public void setUserByUpdatedby(User userByUpdatedby) {
		this.userByUpdatedby = userByUpdatedby;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMAGE_ID")
	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID", nullable = false)
	public Merchant getMerchant() {
		return this.merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISCOUNT_ID")
	public Discount getDiscount() {
		return this.discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_CATEGORY_ID", nullable = false)
	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UOM_ID", nullable = false)
	public Uom getUom() {
		return this.uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_TYPE_ID", nullable = false)
	public ProductType getProductType() {
		return this.productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EDIBLE_TYPE", length = 45)
	public String getEdibleType() {
		return this.edibleType;
	}

	public void setEdibleType(String edibleType) {
		this.edibleType = edibleType;
	}

	@Column(name = "GROUP_COUNT")
	public Integer getGroupCount() {
		return this.groupCount;
	}

	public void setGroupCount(Integer groupCount) {
		this.groupCount = groupCount;
	}

	@Column(name = "BRAND", length = 45)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "PRICE", nullable = false, precision = 15)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "IS_YOUR_HOT", length = 1)
	public Character getIsYourHot() {
		return this.isYourHot;
	}

	public void setIsYourHot(Character isYourHot) {
		this.isYourHot = isYourHot;
	}

	@Column(name = "UNIT", nullable = false, precision = 15)
	public BigDecimal getUnit() {
		return this.unit;
	}

	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<ProductOfferLine> getProductOfferLines() {
		return this.productOfferLines;
	}

	public void setProductOfferLines(List<ProductOfferLine> productOfferLines) {
		this.productOfferLines = productOfferLines;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<MyCart> getMyCarts() {
		return this.myCarts;
	}

	public void setMyCarts(List<MyCart> myCarts) {
		this.myCarts = myCarts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<SalesOrderLine> getSalesOrderLines() {
		return this.salesOrderLines;
	}

	public void setSalesOrderLines(List<SalesOrderLine> salesOrderLines) {
		this.salesOrderLines = salesOrderLines;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<MovementLine> getMovementLines() {
		return this.movementLines;
	}

	public void setMovementLines(List<MovementLine> movementLines) {
		this.movementLines = movementLines;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<ProductImage> getProductImages() {
		return this.productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<ProductOffer> getProductOffers() {
		return this.productOffers;
	}

	public void setProductOffers(List<ProductOffer> productOffers) {
		this.productOffers = productOffers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<ProductInventory> getProductInventories() {
		return this.productInventories;
	}

	public void setProductInventories(List<ProductInventory> productInventories) {
		this.productInventories = productInventories;
	}

}
