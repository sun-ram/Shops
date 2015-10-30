package com.mitosis.aviate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.mysql.jdbc.Blob;

@XmlRootElement
@Entity
@Table(name = "product_image")
public class ProductImages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="image_id")
	private Long imageId;
	@Column(name="product_id")
	private Long productId;
	@Column(name="image_name")
	private String imageName;
	@Column(name="image_Type")
	private String imageType;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="image_position")
	private String imagePosition;
	
	private byte[] image;
	@XmlTransient
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
