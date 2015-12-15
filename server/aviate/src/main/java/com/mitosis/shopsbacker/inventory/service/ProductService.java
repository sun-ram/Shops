package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Uom;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
/**
 * @author RiyazKhan.M
 */
public interface ProductService<T> {
	
	public List<Product> getProductListByType(ProductType productType);
	
	public Product getProduct(String productId);
	
	public List<Product> getProductListByCategoty(ProductCategory productCategory);
	
	public void deleteProduct(Product product) throws Exception;
	
	public void addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public List<Product> getProductByUom(Uom uom);
	
	public Product setProduct(ProductVo productVo,Image img)throws Exception;
	
	public ProductVo setProductVo(Product product)throws Exception;

	public List<Product> getTopProduct(Merchant merchant);
	
	public List<Product> getProductByMerchant(Merchant merchant);
	
	public void productImageUpload(ImageVo imageVo,Merchant merchant) throws Exception ;
	
	public Product getProductByName(String param);
	
	public Product setProductFromExcel(ProductVo productVo) throws Exception ;

	public Product getProductByName(String param, Merchant merchant);
	
	public List<Product> getProductListByName(String name,Merchant merchant);

	public List<Product> getProductName(String productId, String name,
			Merchant merchant);
}
