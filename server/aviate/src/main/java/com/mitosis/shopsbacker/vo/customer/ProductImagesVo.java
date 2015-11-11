package com.mitosis.shopsbacker.vo.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductImagesVo {
	private Long imageId;
	private Long productId;
	private String imageName;
	private String imageType;
	private String imageUrl;
	private String imagePosition;
	
	private byte[] image;
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Long getImageId() {
		return imageId;
	}
	public Long getProductId() {
		return productId;
	}
	@XmlTransient
	public String getImageType() {
		return imageType;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImagePosition() {
		return imagePosition;
	}
	public void setImagePosition(String imagePosition) {
		this.imagePosition = imagePosition;
	}
}
