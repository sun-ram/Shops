package com.mitosis.shopsbacker.vo.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.ProductImage;
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
	private Discount discount;
	private ProductCategoryVo productCategory;
	private UomVo uom;
	private ProductTypeVo productType;
	private String name;
	private String edibleType;
	private Integer groupCount;
	private String brand;
	private BigDecimal price;
	private boolean isYourHot;
	private BigDecimal unit;
	private String description;
	private List<ImageVo> productImages = new ArrayList<ImageVo>();
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
	public Discount getDiscount() {
		return discount;
	}
	public void setDiscount(Discount discount) {
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
	public List<ImageVo> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<ImageVo> productImages) {
		this.productImages = productImages;
	}
	public void setYourHot(boolean isYourHot) {
		this.isYourHot = isYourHot;
	}


}
