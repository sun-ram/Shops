package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.IOException;
import java.io.Serializable;
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
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Uom;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;
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
	@Transactional
	public List<Product> getProductListByType(ProductType productType) {
		return productDao.getProductListByType(productType);
	}

	@Override
	@Transactional
	public Product getProduct(String productId) {
		return productDao.getProduct(productId);
	}

	@Override
	@Transactional
	public List<Product> getProductListByCategoty(
			ProductCategory productCategory) {
		return productDao.getProductListByCategoty(productCategory);
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);

	}

	@Override
	@Transactional
	public void addProduct(Product product) {
		productDao.addProduct(product);

	}

	@Override
	@Transactional
	public void updateProduct(Product product) {
		productDao.updateProduct(product);

	}

	@Override
	@Transactional
	public List<Product> getProductByUom(Uom uom){
		return productDao.getProductByUom(uom);
	}

	@Override
	@Transactional
	public List<Product> getTopProduct(Merchant merchant) {
		return productDao.getTopProduct(merchant);
	}
	
	@Override
	@Transactional
	public List<Product> getProductByMerchant(Merchant merchant) {
		return productDao.getProductByMerchant(merchant);
	}
	@Override
	@Transactional
	public void productImageUpload(ProductVo productVo,Merchant merchant) throws Exception {
		
		if (productVo.getImage().getImage() == null) {
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
		if (CommonUtil.uploadImage(productVo.getImage().getImage(), productVo
				.getImage().getType(), defaultImagePath + productImagePath,
				imageName)) {
			productVo.getImage().setName(imageName);
			productVo.getImage().setUrl(
					productImagePath + imageName + "."
							+ productVo.getImage().getType());
		}
	}

	@Override
	public Product setProduct(ProductVo productVo,Image img) throws Exception {
		Product product = null;
		if(productVo.getProductId() == null){
			product = (Product) CommonUtil.setAuditColumnInfo(Product.class.getName());
			product.setIsactive('Y');
		}else{
			product = productDao.getProduct(productVo.getProductId());
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
		
		return productVo;
	}




}
