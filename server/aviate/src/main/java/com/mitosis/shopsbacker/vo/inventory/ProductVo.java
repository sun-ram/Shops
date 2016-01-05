package com.mitosis.shopsbacker.vo.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.ProductImage;
import com.mitosis.shopsbacker.model.ProductOfferLine;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;

/**
 * @author Riyaz Khan
 *
 */
@XmlRootElement
public class ProductVo {
	
	
	private String productId;
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
	private BigDecimal wasPrice; 
	private boolean isYourHot;
	private BigDecimal unit;
	private String description;
	private String productTypeId;
	private boolean isKit;
	private boolean isChild;
	private boolean isBundle;
	private List<ProductImageVo> productImages = new ArrayList<ProductImageVo>();
	private List<ImageVo> images = new ArrayList<ImageVo>();
	private List<ProductOfferVo> productOffer = new ArrayList<ProductOfferVo>();
	private String userId;
	public List<ImageVo> getImages() {
		return images;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setImages(List<ImageVo> images) {
		this.images = images;
	}
	
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	
	public BigDecimal getWasPrice() {
		return wasPrice;
	}
	public void setWasPrice(BigDecimal wasPrice) {
		this.wasPrice = wasPrice;
	}
	public boolean getIsYourHot() {
		return isYourHot;
	}
	public void setIsYourHot(boolean isYourHot) {
		this.isYourHot = isYourHot;
	}
	public BigDecimal getUnit() {
		return unit;
	}
	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ProductImageVo> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<ProductImageVo> productImages) {
		this.productImages = productImages;
	}
	public void setYourHot(boolean isYourHot) {
		this.isYourHot = isYourHot;
	}
	public Boolean getIsKit() {
		return isKit;
	}
	public void setIsKit(Boolean isKit) {
		this.isKit = isKit;
	}
	public Boolean getIsChild() {
		return isChild;
	}
	public void setIsChild(Boolean isChild) {
		this.isChild = isChild;
	}
	public boolean getIsBundle() {
		return isBundle;
	}
	public void setIsBundle(boolean isBundle) {
		this.isBundle = isBundle;
	}
	public List<ProductOfferVo> getProductOffer() {
		return productOffer;
	}
	public void setProductOfferLines(List<ProductOfferVo> productOffer) {
		this.productOffer = productOffer;
	}


}
