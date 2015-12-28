package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.inventory.service.ProductCategoryService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.ProductCategoryResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;
import com.mitosis.shopsbacker.vo.inventory.ProductTypeVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;

@Path("productcategory")
@Controller("productCategoryRestServices")
public class ProductCategoryRestServices<T> {

	@Autowired
	ProductCategoryService<T> productCategoryService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	ProductTypeService<T> productTypeService;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	ProductService<T> productService;

	@Path("/addparentcategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductCategoryResponseVo addparentcategory(
			ProductCategoryVo productCategoryVo) {
		boolean flag = false;
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		try {
			flag = productCategoryNameChecking(productCategoryVo);
			if (!flag) {
				ProductCategory productCategory = setProductCategoryDetails(productCategoryVo);
				productCategory.setIsactive('Y');
				productCategoryService.addCategory(productCategory);
				if (productCategory.getProductCategoryId() == null) {
					productCategoryResponseVo.setStatus(SBMessageStatus.FAILURE
							.getValue());
					productCategoryResponseVo
							.setErrorCode(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
									.getCode());
					productCategoryResponseVo
							.setErrorString(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
									.getMessage());
					return productCategoryResponseVo;
				} else {
					productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
							.getValue());
				}
			} else {
				productCategoryResponseVo.setStatus(SBMessageStatus.FAILURE
						.getValue());
				productCategoryResponseVo
						.setErrorCode(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
								.getCode());
				productCategoryResponseVo
						.setErrorString(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
								.getMessage());
				return productCategoryResponseVo;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productCategoryResponseVo;

	}

	@Path("/addcategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductCategoryResponseVo addCategory(
			ProductCategoryVo productCategoryVo) {
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		ProductCategory productCategory = new ProductCategory();
		boolean flag = false;
		try {
			productCategory = productCategoryService
					.getCategoryById(productCategoryVo.getCategory()
							.getProductCategoryId());
			flag = productTypeService.checkProductType(productCategory);
			if (!flag) {
				productCategoryResponseVo.setStatus(SBMessageStatus.FAILURE
						.getValue());
				productCategoryResponseVo
						.setErrorCode(SBErrorMessage.CATEGORY_ALREADY_HAS_PRODUCT_TYPE
								.getCode());
				productCategoryResponseVo
						.setErrorString(SBErrorMessage.CATEGORY_ALREADY_HAS_PRODUCT_TYPE
								.getMessage());
				return productCategoryResponseVo;
			}
			List<ProductCategory> productCategoryName = productCategory
					.getProductCategories();
			for (ProductCategory name : productCategoryName) {
				if (name.getName()
						.equalsIgnoreCase(productCategoryVo.getName())) {
					productCategoryResponseVo.setStatus(SBMessageStatus.FAILURE
							.getValue());
					productCategoryResponseVo
							.setErrorCode(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
									.getCode());
					productCategoryResponseVo
							.setErrorString(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
									.getMessage());
					return productCategoryResponseVo;
				}
			}
			ProductCategory productCategoryAdd = setProductCategoryDetails(productCategoryVo);
			productCategoryAdd.setParentCategory(productCategory);
			productCategoryAdd.setIsactive('Y');
			productCategoryService.addCategory(productCategoryAdd);
			if (productCategoryAdd.getProductCategoryId() == null) {
				productCategoryResponseVo.setStatus(SBMessageStatus.FAILURE
						.getValue());
				productCategoryResponseVo
						.setErrorCode(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
								.getCode());
				productCategoryResponseVo
						.setErrorString(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
								.getMessage());
				return productCategoryResponseVo;
			} else {
				productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
						.getValue());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return productCategoryResponseVo;

	}

	@Path("/updatecategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductCategoryResponseVo updateCategory(
			ProductCategoryVo productCategoryVo) {
		boolean flag = false;
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		if (productCategoryVo.getCategoryType().equalsIgnoreCase("Category")) {
			flag=productCategoryNameChecking(productCategoryVo);
			if(!flag){
				ProductCategory productCategory = productCategoryService
						.getCategoryById(productCategoryVo.getProductCategoryId());
				if (productCategory != null) {
					productCategory.setName(productCategoryVo.getName());
					productCategoryService.updateCategory(productCategory);
					productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
							.getValue());
					return productCategoryResponseVo;
				}
			}
			else{
				productCategoryResponseVo.setStatus(SBMessageStatus.FAILURE
						.getValue());
				productCategoryResponseVo
						.setErrorCode(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
								.getCode());
				productCategoryResponseVo
						.setErrorString(SBErrorMessage.PRODUCT_CATEGORY_ALREADY_EXITS
								.getMessage());
				return productCategoryResponseVo;
			}

		} else {
			ProductCategory productCategory = productCategoryService
					.getCategoryById(productCategoryVo.getProductCategoryId());
			if (productCategory != null) {
				productCategory.setName(productCategoryVo.getName());
				productCategoryService.updateCategory(productCategory);
				productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
						.getValue());
			}

		}
		return productCategoryResponseVo;
	}

	private boolean productCategoryNameChecking(
			ProductCategoryVo productCategoryVo) {
		boolean flag = false;
		Merchant merchant = merchantService.getMerchantById(productCategoryVo
				.getMerchant().getMerchantId());
		if (merchant != null) {
			List<ProductCategory> productCategoryList = productCategoryService
					.getRootProductCategoryList(merchant);
			for (ProductCategory pd : productCategoryList) {
				if (pd.getName().equalsIgnoreCase(productCategoryVo.getName())) {
					flag = true;
					break;
				} else {
					flag = false;
				}

			}
		}
		return flag;
	}

	@Path("/removecategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductCategoryResponseVo removeCategory(
			ProductCategoryVo productCategoryVo) {
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		ProductCategory productCategory = productCategoryService
				.getCategoryById(productCategoryVo.getProductCategoryId());
		List<Product> productList = productCategory.getProducts();
		if (productList.isEmpty()) {
			List<ProductCategory> childCategories = productCategory
					.getProductCategories();
			productList = getProductsFromCatogories(productList,
					childCategories);
		}
		if (productCategory != null && productList.size() == 0) {
			productCategoryService.deleteCategory(productCategory);
			productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
		} else {
			productCategoryResponseVo.setStatus(SBMessageStatus.FAILURE
					.getValue());
			productCategoryResponseVo
					.setErrorCode(SBErrorMessage.CATEGORY_PRODUCT_CHECK
							.getCode());
			productCategoryResponseVo
					.setErrorString(SBErrorMessage.CATEGORY_PRODUCT_CHECK
							.getMessage());
		}
		return productCategoryResponseVo;
	}

	private List<Product> getProductsFromCatogories(List<Product> productList,
			List<ProductCategory> childCategories) {
		for (ProductCategory childCategory : childCategories) {
			List<Product> listOfproducts = childCategory.getProducts();
			if (productList.isEmpty() && !listOfproducts.isEmpty()) {
				productList = listOfproducts;
			} else if (!listOfproducts.isEmpty()) {
				productList.addAll(listOfproducts);
			}
			List<ProductCategory> listOfChildCategories = childCategory
					.getProductCategories();
			if (!listOfChildCategories.isEmpty()) {
				productList = getProductsFromCatogories(productList,
						listOfChildCategories);
			}
		}
		return productList;
	}

	@Path("/getcategories")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductCategoryResponseVo getCategoryList(
			ProductCategoryVo productCategoryVo) {
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		Merchant merchant = merchantService.getMerchantById(productCategoryVo
				.getMerchant().getMerchantId());
		if (merchant != null) {
			List<ProductCategory> parentCategories = productCategoryService
					.getRootProductCategoryList(merchant);
			List<ProductCategoryVo> rootProductCategoryVoList = new ArrayList<ProductCategoryVo>();
			Map<String, ProductCategoryVo> productCategoryVoParentMap = new HashMap<String, ProductCategoryVo>();
			ProductCategoryVo prodCategoryVo = new ProductCategoryVo();
			getHierarchicalProductCategorys(parentCategories, prodCategoryVo,
					rootProductCategoryVoList, productCategoryVoParentMap);
			productCategoryResponseVo
					.setProductCategories(rootProductCategoryVoList);
			productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
			// productCategoryResponseVo=productCategory.
		}
		return productCategoryResponseVo;
	}

	@Path("/getallcategorylist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductCategoryResponseVo getCategoryListData(
			ProductCategoryVo productCategoryVo) {
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		Merchant merchant = merchantService.getMerchantById(productCategoryVo
				.getMerchant().getMerchantId());
		if (merchant != null) {
			List<ProductCategory> parentCategories = productCategoryService
					.getRootProductCategoryList(merchant);
			List<ProductCategoryVo> rootProductCategoryVoList = new ArrayList<ProductCategoryVo>();
			Map<String, ProductCategoryVo> productCategoryVoParentMap = new HashMap<String, ProductCategoryVo>();
			ProductCategoryVo prodCategoryVo = new ProductCategoryVo();
			getHierarchicalProductCategorys(parentCategories, prodCategoryVo,
					rootProductCategoryVoList, productCategoryVoParentMap);
			productCategoryResponseVo
					.setProductCategories(rootProductCategoryVoList);
			productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
			// productCategoryResponseVo=productCategory.
		}
		return productCategoryResponseVo;
	}

	@Path("/getallleafcategorylist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getallleafcategorylist(ProductCategoryVo productCategoryVo)
			throws Exception {
		ObjectMapper mapper = CommonUtil.getObjectMapper();
		String responseString = null;
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		List<ProductCategory> productCategoryLeafList = new ArrayList<ProductCategory>();
		List<ProductCategoryVo> productCategoryLeafListVo = new ArrayList<ProductCategoryVo>();
		Merchant merchant = null;
		if (productCategoryVo.getMerchant() != null
				&& productCategoryVo.getMerchant().getMerchantId() != null) {
			merchant = merchantService.getMerchantById(productCategoryVo
					.getMerchant().getMerchantId());
		} else if (productCategoryVo.getMerchantId() != null) {
			merchant = merchantService.getMerchantById(productCategoryVo
					.getMerchantId());
		} else if (productCategoryVo.getStoreId() != null) {
			Store store = storeService.getStoreById(productCategoryVo
					.getStoreId());
			merchant = store.getMerchant();
		}
		if (merchant != null) {
			productCategoryLeafList = productCategoryService
					.getallleafcategorylist(merchant);
			for (ProductCategory productSingleLeaf : productCategoryLeafList) {
				ProductCategoryVo productCategoryLeaf = new ProductCategoryVo();
				productCategoryLeaf.setProductCategoryId(productSingleLeaf
						.getProductCategoryId());
				productCategoryLeaf.setName(productSingleLeaf.getName());

				List<ProductType> productTypes = productSingleLeaf
						.getProductTypes();
				List<ProductTypeVo> productTypeVos = new ArrayList<ProductTypeVo>();
				for (ProductType productType : productTypes) {
					ProductTypeVo productTypeVo = new ProductTypeVo();
					productTypeVo.setProductTypeId(productType
							.getProductTypeId());
					productTypeVo.setName(productType.getName());
					productTypeVo.setProductCategory(null);
					productTypeVos.add(productTypeVo);
				}
				productCategoryLeaf.setProductTypes(productTypeVos);
				productCategoryLeaf.setProducts(null);
				productCategoryLeaf.setCategoriesVo(null);
				productCategoryLeafListVo.add(productCategoryLeaf);
			}
			productCategoryResponseVo
					.setProductCategories(productCategoryLeafListVo);
			productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
		}
		responseString = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(productCategoryResponseVo);
		return responseString;

	}

	@Path("/getallleafcategorylistWithProducts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ProductCategoryResponseVo getallleafcategorylistWithProducts(
			ProductCategoryVo productCategoryVo) throws Exception {
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		List<ProductCategory> productCategoryLeafList = new ArrayList<ProductCategory>();
		List<ProductCategoryVo> productCategoryLeafListVo = new ArrayList<ProductCategoryVo>();
		Merchant merchant = merchantService.getMerchantById(productCategoryVo
				.getMerchant().getMerchantId());
		if (merchant != null) {
			productCategoryLeafList = productCategoryService
					.getallleafcategorylist(merchant);
			for (ProductCategory productSingleLeaf : productCategoryLeafList) {
				ProductCategoryVo productCategoryLeaf = new ProductCategoryVo();
				productCategoryLeaf.setProductCategoryId(productSingleLeaf
						.getProductCategoryId());
				productCategoryLeaf.setName(productSingleLeaf.getName());
				List<Product> productList = productSingleLeaf.getProducts();
				if (!productList.isEmpty()) {
					List<ProductVo> producVoList = new ArrayList<ProductVo>();
					for (int i = 0; i < productList.size(); i++) {
						ProductVo product = productService
								.setProductVo(productList.get(i));
						System.out.println(product.getProductId());
						producVoList.add(product);
					}
					productCategoryLeaf.setProducts(producVoList);
				}
				productCategoryLeafListVo.add(productCategoryLeaf);
			}
			productCategoryResponseVo
					.setProductCategories(productCategoryLeafListVo);
			productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
		}
		return productCategoryResponseVo;

	}

	private void getHierarchicalProductCategorys(
			List<ProductCategory> productCategories,
			ProductCategoryVo prodCategoryVo,
			List<ProductCategoryVo> rootProductCategoryVoList,
			Map<String, ProductCategoryVo> productCategoryVoMap) {
		for (ProductCategory productCategory : productCategories) {
			ProductCategoryVo productCategoryVo = new ProductCategoryVo();
			productCategoryVo.setName(productCategory.getName());
			productCategoryVo.setProductCategoryId(productCategory
					.getProductCategoryId());
			productCategoryVoMap.put(productCategory.getProductCategoryId(),
					productCategoryVo);
			if (productCategory.getParentCategory() == null) {
				rootProductCategoryVoList.add(productCategoryVo);
			} else {

				String parentCategoryId = productCategory.getParentCategory()
						.getProductCategoryId();
				ProductCategoryVo parentCategoryVO = productCategoryVoMap
						.get(parentCategoryId);
				List<ProductCategoryVo> listOfChildCategoryVo = parentCategoryVO
						.getCategoriesVo();
				listOfChildCategoryVo.add(productCategoryVo);
			}

			if (productCategory.getProductCategories().size() > 0) {
				getHierarchicalProductCategorys(
						productCategory.getProductCategories(),
						productCategoryVo, rootProductCategoryVoList,
						productCategoryVoMap);
			}
		}
	}

	public ProductCategory setProductCategoryDetails(
			ProductCategoryVo ProductCategoryVo) throws Exception {
		Merchant merchant = new Merchant();
		ProductCategory productCategory = (ProductCategory) CommonUtil
				.setAuditColumnInfo(ProductCategory.class.getName(), null);
		productCategory.setName(ProductCategoryVo.getName());
		merchant = merchantService.getMerchantById(ProductCategoryVo
				.getMerchant().getMerchantId());
		if (merchant != null) {
			productCategory.setMerchant(merchant);
		}
		return productCategory;

	}

	/*
	 * Based on the store id to taking the product category for customer app
	 * side navigation functionality api
	 */

	@Path("store/getallcategorylist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getallleafcategorylist1(StoreVo storeVo) throws Exception {
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		Store store = storeService.getStoreById(storeVo.getStoreId());
		if (store != null) {
			productCategoryResponseVo = getCategoryListDataForStore(store
					.getMerchant().getMerchantId());
		}
		return CommonUtil.getObjectMapper(productCategoryResponseVo);
	}

	public ProductCategoryResponseVo getCategoryListDataForStore(
			String merchantId) {
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		Merchant merchant = merchantService.getMerchantById(merchantId);
		if (merchant != null) {
			List<ProductCategory> parentCategories = productCategoryService
					.getRootProductCategoryList(merchant);
			List<ProductCategoryVo> rootProductCategoryVoList = new ArrayList<ProductCategoryVo>();
			Map<String, ProductCategoryVo> productCategoryVoParentMap = new HashMap<String, ProductCategoryVo>();
			ProductCategoryVo prodCategoryVo = new ProductCategoryVo();
			getHierarchicalProductCategorys(parentCategories, prodCategoryVo,
					rootProductCategoryVoList, productCategoryVoParentMap);

			List<ProductTypeVo> productTypeVos = productTypeService
					.prepareProductTypeVoList(merchant);

			for (ProductTypeVo productTypeVo : productTypeVos) {

				String productCategoryId = productTypeVo.getProductCategory()
						.getProductCategoryId();
				ProductCategoryVo productCategoryVo = productCategoryVoParentMap
						.get(productCategoryId);
				List<ProductTypeVo> productTypes = productCategoryVo
						.getProductTypes();
				productTypes.add(productTypeVo);

			}
			productCategoryResponseVo
					.setProductCategories(rootProductCategoryVoList);
			productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
			// productCategoryResponseVo=productCategory.
		}
		return productCategoryResponseVo;
	}

}
