package com.mitosis.shopsbacker.vo.inventory;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.vo.common.ImageVo;

/**
 * @author Riyaz Khan
 *
 */
public class ProductImageVo {
	
	private String productImageId;
	
	private ImageVo image ;
	
	private ProductVo product;

	public String getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(String productImageId) {
		this.productImageId = productImageId;
	}

	public ImageVo getImage() {
		return image;
	}

	public void setImage(ImageVo image) {
		this.image = image;
	}

	public ProductVo getProduct() {
		return product;
	}

	public void setProduct(ProductVo product) {
		this.product = product;
	}

	
}
