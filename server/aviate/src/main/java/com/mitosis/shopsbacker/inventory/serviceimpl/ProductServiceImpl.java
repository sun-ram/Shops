package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.inventory.dao.ProductDao;
import com.mitosis.shopsbacker.inventory.service.ProductCategoryService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.inventory.service.UomService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductImage;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Uom;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;
import com.mitosis.shopsbacker.vo.inventory.ProductImageVo;
import com.mitosis.shopsbacker.vo.inventory.ProductTypeVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.mitosis.shopsbacker.vo.inventory.UomVo;
/**
 * @author RiyazKhan.M
 */
@Service("productServiceImpl")
public class ProductServiceImpl<T> implements ProductService<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProductDao<T> productDao;

	@Autowired
	ImageService<T> imageService;

	@Autowired
	UomService<T> uomService;
	
	@Autowired
	ProductCategoryService<T> productCategoryService;
	
	@Autowired
	ProductTypeService<T> productTypeService;
	

	@Override
	public List<Product> getProductListByType(ProductType productType) {
		return productDao.getProductListByType(productType);
	}

	@Override
	public Product getProduct(String productId) {
		return productDao.getProduct(productId);
	}

	@Override
	public List<Product> getProductListByCategoty(
			ProductCategory productCategory) {
		return productDao.getProductListByCategoty(productCategory);
	}

	@Override
	public void deleteProduct(Product product) throws Exception {
		Image image = imageService.getImageById(product.getImage().getImageId());
		productDao.deleteProduct(product);
		
		String defaultImagePath = "";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		defaultImagePath = properties.getProperty("imagePath");
		CommonUtil.removeImage(defaultImagePath.concat(image.getUrl()));

	}

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);

	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);

	}

	@Override
	public List<Product> getProductByUom(Uom uom){
		return productDao.getProductByUom(uom);
	}

	@Override
	public List<Product> getTopProduct(Merchant merchant) {
		return productDao.getTopProduct(merchant);
	}
	
	@Override
	public List<Product> getProductByMerchant(Merchant merchant) {
		return productDao.getProductByMerchant(merchant);
	}
	@Override
	public void productImageUpload(ImageVo imageVo,Merchant merchant) throws Exception {
		
		if (imageVo.getImage() == null) {
			return;
		}

		String productImagePath = "";
		String defaultImagePath = "";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		defaultImagePath = properties.getProperty("imagePath");
		productImagePath = "merchant/" + merchant.getName() + "/" + "product" + "/" ;

		String imageName = UUID.randomUUID().toString().replace("-", "");
		if (CommonUtil.uploadImage(imageVo.getImage(), imageVo
				.getType(), defaultImagePath + productImagePath,
				imageName)) {
			imageVo.setName(imageName);
			imageVo.setUrl(
					productImagePath + imageName + "."
							+ imageVo.getType());
		}
	}

	@Override
	public Product setProduct(ProductVo productVo,Image img) throws Exception {
		Product product = null;
		if(productVo.getProductId() == null){
			product = (Product) CommonUtil.setAuditColumnInfo(Product.class.getName());
			product.setIsactive('Y');
		}else{
			
				product = productDao.getProductByName(productVo.getName());
			
			product.setUpdated(new Date());
			//TODO need to get user from session and set to updatedby
			product.setUpdatedby("123");
			if (productVo.getImage().getImage() != null
					&& productVo.getImage().getType() != null) {
				img = product.getImage();
			}
		}
		if (productVo.getImage().getImage() != null) {
			Image image = imageService.setImage(productVo.getImage());
			product.setImage(image);
		}
		product.setName(productVo.getName());
		product.setPrice(productVo.getPrice());
		product.setEdibleType(productVo.getEdibleType());
		product.setGroupCount(productVo.getGroupCount());
		product.setBrand(productVo.getBrand());
		product.setUnit(productVo.getUnit());
		product.setDescription(productVo.getDescription());
		if(productVo.getIsYourHot()){
			product.setIsYourHot('Y');
		}else{
			product.setIsYourHot('N');
		}

		Image image = imageService.setImage(productVo.getImage());
		product.setImage(image);



		return product;
	}

	@Override
	public ProductVo setProductVo(Product product) throws Exception {
		ProductVo productVo = new ProductVo();
		
		productVo.setName(product.getName());
		productVo.setBrand(product.getBrand());
		productVo.setDescription(product.getDescription());
		productVo.setPrice(product.getPrice());
		productVo.setUnit(product.getUnit());
		productVo.setEdibleType(product.getEdibleType());
		productVo.setGroupCount(product.getGroupCount());
		productVo.setProductId(product.getProductId());
		if(product.getImage() != null){
		ImageVo image = imageService.setImageVo(product.getImage());
		productVo.setImage(image);
		}
		ProductCategoryVo productCategoryVo = productCategoryService.setProductCategoryVo(product.getProductCategory());
		productVo.setProductCategory(productCategoryVo);
		
		ProductTypeVo productTypeVo = productTypeService.setProductTypeVo(product.getProductType());
		productVo.setProductType(productTypeVo);
		
		UomVo uomVo = uomService.setUomVo(product.getUom());
		
		productVo.setUom(uomVo);
		
		List<ProductImage> productImages = product.getProductImages();
		List<ProductImageVo> productImageVos=new  ArrayList<ProductImageVo>();
		for(ProductImage productImage:productImages){
			ProductImageVo productImageVo= new  ProductImageVo();
			ImageVo image = imageService.setImageVo(productImage.getImage());
			productImageVo.setImage(image);
			ProductVo productvo = new ProductVo();
			productvo.setProductId(product.getProductId());
			productvo.setName(product.getName());
			productImageVo.setProduct(productvo);
			productImageVo.setProductImageId(productImage.getProductImageId());
			productImageVos.add(productImageVo);
		}
		productVo.setProductImages(productImageVos);
		return productVo;
	}
	
	@Override
	public Product setProductFromExcel(ProductVo productVo) throws Exception {
		Product product = null;
		BigDecimal bg = null;
		Long l = new Long("12345678");
        bg = BigDecimal.valueOf(l);
		if(productVo.getIsYourHot()){
			product = (Product) CommonUtil.setAuditColumnInfo(Product.class.getName());
			product.setIsactive('Y');
		}else{
			product = productDao.getProductByName(productVo.getName());
			
			product.setUpdated(new Date());
			//TODO need to get user from session and set to updatedby
			product.setUpdatedby("123");
			
		}
		/*product.setName(productVo.getName());
		product.setPrice(bg);
		product.setEdibleType(productVo.getEdibleType());
		product.setGroupCount(productVo.getGroupCount());
		product.setBrand(productVo.getBrand());
		product.setUnit(productVo.getUnit());*/
		product.setDescription("Description");
		/*if(productVo.getIsYourHot()){
			product.setIsYourHot('Y');
		}else{
			product.setIsYourHot('N');
		}*/
		return product;
	}
	
	@Override
	public Product getProductByName(String param) {
		return productDao.getProductByName(param);
	}




}
