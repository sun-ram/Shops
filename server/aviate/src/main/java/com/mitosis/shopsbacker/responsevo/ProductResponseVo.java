package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;


public class ProductResponseVo extends ResponseModel{
	
	public List<ProductVo> products;
	
	public ProductVo product;
	
	String fileInByteArrayString;

	public String getFileInByteArrayString() {
		return fileInByteArrayString;
	}

	public void setFileInByteArrayString(String fileInByteArrayString) {
		this.fileInByteArrayString = fileInByteArrayString;
	}

	public List<ProductVo> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVo> product) {
		this.products = product;
	}

	public void setProduct(ProductVo product) {
		this.product = product;
	}
	
	public  ProductVo getProduct() {
		return product;
	}



	

	

}
