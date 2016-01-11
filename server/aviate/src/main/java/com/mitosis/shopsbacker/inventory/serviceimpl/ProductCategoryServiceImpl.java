package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.inventory.dao.ProductCategoryDao;
import com.mitosis.shopsbacker.inventory.service.DiscountService;
import com.mitosis.shopsbacker.inventory.service.ProductCategoryService;
import com.mitosis.shopsbacker.inventory.service.ProductInventoryService;
import com.mitosis.shopsbacker.inventory.service.ProductOfferLineService;
import com.mitosis.shopsbacker.inventory.service.ProductOfferService;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.inventory.service.UomService;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductImage;
import com.mitosis.shopsbacker.model.ProductInventory;
import com.mitosis.shopsbacker.model.ProductOffer;
import com.mitosis.shopsbacker.model.ProductOfferLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.common.ImageVo;
import com.mitosis.shopsbacker.vo.inventory.DiscountVo;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;
import com.mitosis.shopsbacker.vo.inventory.ProductInventoryVo;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferLineVo;
import com.mitosis.shopsbacker.vo.inventory.ProductOfferVo;
import com.mitosis.shopsbacker.vo.inventory.ProductTypeVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.mitosis.shopsbacker.vo.inventory.UomVo;

/**
 * @author RiyazKhan.M
 */
@Service("productCategoryServiceImpl")
public class ProductCategoryServiceImpl<T> implements
		ProductCategoryService<T>, Serializable {

	@Autowired
	ProductCategoryDao productCategoryDao;
	
	@Autowired
	ProductOfferService<T> productOfferService;
	
	@Autowired
	ProductOfferLineService<T> productOfferLineService;
	
	@Autowired
	ImageService<T> imageService;
	
	@Autowired
	ProductTypeService<T> productTypeService;
	
	@Autowired
	UomService<T> uomService;
	
	@Autowired
	ProductInventoryService<T> productInventoryService;
	
	@Autowired
	DiscountService<T> discountService;

	 
	public ProductCategoryDao getProductCategoryDao() {
		return productCategoryDao;
	}

	public void setProductCategoryDao(ProductCategoryDao productCategoryDao) {
		this.productCategoryDao = productCategoryDao;
	}

	@Override
	public List<ProductCategory> getCategoryListByStore(Store store) {
		return productCategoryDao.getCategoryListByStore(store);
	}

	@Override
	public ProductCategory getCategoryById(String categoryId) {
		return productCategoryDao.getCategoryById(categoryId);
	}

	@Override
	public void getLeafCategories(List<ProductCategory> productCategoryList,
			List<ProductCategory> productCategories) {
		for (ProductCategory productCategory : productCategoryList) {
			if (productCategory.getProductCategories().size() > 0) {
				getLeafCategories(productCategory.getProductCategories(),
						productCategories);
			} else {
				ProductCategory category = new ProductCategory();
				category.setProductCategoryId(productCategory
						.getProductCategoryId());
				category.setName(productCategory.getName());
				category.setStore(productCategory.getStore());
				productCategories.add(category);
			}
		}
	}

	@Override
	public void setCategoryIds(List<ProductCategory> productCategoryList,
			List<String> ids) {
		for (ProductCategory productCategory : productCategoryList) {
			ids.add(productCategory.getProductCategoryId());
			if (productCategory.getProductCategories().size() > 0) {
				setCategoryIds(productCategory.getProductCategories(), ids);
			}
		}
	}

	@Override
	public void setParentCategories(List<ProductCategory> productCategoryList,
			List<ProductCategory> productCategories) {
		for (ProductCategory productCategory : productCategoryList) {
			ProductCategory category = new ProductCategory();
			category.setProductCategoryId(productCategory
					.getProductCategoryId());
			category.setName(productCategory.getName());
			productCategories.add(category);
			if (productCategory.getProductCategories().size() > 0) {
				setParentCategories(productCategory.getProductCategories(),
						productCategories);
			}
		}
	}

	@Override
	public List<ProductCategory> getProductCategoryList(Merchant merchant) {
		return productCategoryDao.getProductCategoryList(merchant);
	}

	@Override
	public void addCategory(ProductCategory productCategory) {
		productCategoryDao.addCategory(productCategory);
		
	}

	@Override
	public void updateCategory(ProductCategory productCategory) {
		productCategoryDao.updateCategory(productCategory);
		
	}

	@Override
	public void deleteCategory(ProductCategory productCategory) {
		productCategoryDao.deleteCategory(productCategory);
		
	}

	@Override
	public List<ProductCategory> getRootProductCategoryList(Merchant merchant){
		 return productCategoryDao.getRootProductCategoryList(merchant);
	}

	@Override
	public List<ProductCategory> getallleafcategorylist(Merchant merchant) {
		return productCategoryDao.getallleafcategorylist(merchant);
	}

	@Override
	public ProductCategoryVo setProductCategoryVo(ProductCategory productCategory) {
		ProductCategoryVo category = new ProductCategoryVo();
		category.setProductCategoryId(productCategory
				.getProductCategoryId());
		category.setName(productCategory.getName());
		return category;
	}

	@Override
	public List<ProductCategory> getParentCategory(
			ProductCategory parentCategory) {
		return productCategoryDao.getParentCategory(parentCategory);
	}
	
	@Override
	public ProductVo setProductVo(Product product) throws Exception {
		
	if(product.getIsBundle() =='N' && product.getIsKit() =='N'){	
		ProductVo productVo = new ProductVo();
		productVo.setName(product.getName());
		productVo.setBrand(product.getBrand());
		productVo.setDescription(product.getDescription());
		productVo.setPrice(product.getPrice());
		productVo.setWasPrice(product.getWasPrice());
		productVo.setUnit(product.getUnit());
		productVo.setEdibleType(product.getEdibleType());
		productVo.setGroupCount(product.getGroupCount());
		productVo.setProductId(product.getProductId());
		if (product.getIsBundle() == 'Y') {
			productVo.setIsBundle(true);
		} else {
			productVo.setIsBundle(false);
		}
		if (product.getIsKit() == 'Y') {
			productVo.setIsKit(true);
		} else {
			productVo.setIsKit(false);
		}
		if (product.getIsChild() == 'Y') {
			productVo.setIsChild(true);
		} else {
			productVo.setIsChild(false);
		}
		if (product.getIsYourHot() == 'Y') {
			productVo.setIsYourHot(true);
		} else {
			productVo.setIsYourHot(false);
		}

		if (product.getIsKit() == 'Y' && !product.getProductOffers().isEmpty()) {
			double wasPrice = 0;

			for (ProductOfferLine productOfferLine : product.getProductOffers()
					.get(0).getProductOfferLines()) {
				wasPrice = wasPrice
						+ productOfferLine.getProduct().getPrice().floatValue();
			}
			BigDecimal price = new BigDecimal(wasPrice);
			productVo.setWasPrice(price);
		}
		if (product.getProductOffers() != null
				|| product.getProductOffers().size() != 0) {
			List<ProductOfferVo> productOfferVos = new ArrayList<ProductOfferVo>();

			List<ProductOffer> productOffers = product.getProductOffers();
			for (ProductOffer productOffer : productOffers) {

				ProductOfferVo productOfferVo = productOfferService
						.setProductOfferVo(productOffer);
				productOfferVos.add(productOfferVo);
			}
			productVo.setProductOffers(productOfferVos);
		}

		List<ProductOfferLineVo> productOfferLineVos = new ArrayList<ProductOfferLineVo>();
		for (ProductOfferLine offerLine : product.getProductOfferLines()) {
			ProductOfferLineVo offerLinesVo = productOfferLineService
					.setProductOfferLineVo(offerLine, true);
			productOfferLineVos.add(offerLinesVo);
		}
		productVo.setProductOfferLines(productOfferLineVos);

		if (product.getImage() != null) {
			ImageVo image = imageService.setImageVo(product.getImage());
			productVo.setImage(image);
		}
		ProductCategoryVo productCategoryVo = setProductCategoryVo(product.getProductCategory());
		productVo.setProductCategory(productCategoryVo);

		ProductTypeVo productTypeVo = productTypeService
				.setProductTypeVo(product.getProductType());
		productVo.setProductType(productTypeVo);
		/*
		 * if(product.getDiscount() !=null){ DiscountVo discountVo =
		 * discountService.setDiscountVo(product.getDiscount()); Boolean Vaild =
		 * CommonUtil.validDiscount(discountVo.getStartDate(),
		 * discountVo.getEndDate(), discountVo.getStartTime(),
		 * discountVo.getEndTime()); if(Vaild){
		 * productVo.setDiscount(discountVo); } }
		 */
		UomVo uomVo = uomService.setUomVo(product.getUom());
		productVo.setUom(uomVo);

		List<ProductImage> productImages = product.getProductImages();
		List<ImageVo> productImageVos = new ArrayList<ImageVo>();
		for (ProductImage productImage : productImages) {
			// ProductImageVo productImageVo= new ProductImageVo();
			ImageVo image = imageService.setImageVo(productImage.getImage());
			// productImageVo.setImage(image);
			// ProductVo productvo = new ProductVo();
			// productvo.setProductId(product.getProductId());
			// productvo.setName(product.getName());
			// productImageVo.setProduct(productvo);
			// productImageVo.setProductImageId(productImage.getProductImageId());
			productImageVos.add(image);
		}
		productVo.setImages(productImageVos);

		if (product.getDiscountProducts() != null
				&& product.getDiscountProducts().size() != 0) {

			// Product has only one discount so we using get(0).
			Discount discount = product.getDiscountProducts().get(0)
					.getDiscount();
			DiscountVo discountVo = discountService.setDiscountVo(discount);
			productVo.setDiscount(discountVo);
		}
		List<ProductInventory> productInventoryList=product.getProductInventories();
		ProductInventoryVo productInventoryVo=new ProductInventoryVo();
		if(!productInventoryList.isEmpty()){
			productInventoryVo=productInventoryService.setProductInventory(productInventoryList);
		}
		productVo.setProductInventory(productInventoryVo);
		return productVo;
	}
	return null;
	}
}