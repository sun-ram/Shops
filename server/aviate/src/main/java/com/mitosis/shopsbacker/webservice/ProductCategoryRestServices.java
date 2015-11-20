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

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.inventory.service.ProductCategoryService;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.responsevo.ProductCategoryResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.inventory.ProductCategoryVo;

@Path("productcategory")
@Controller("productCategoryRestServices")
public class ProductCategoryRestServices<T> {

	@Autowired
	ProductCategoryService<T> productCategoryService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	ProductTypeService<T> productTypeService;

	@Path("/addparentcategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryResponseVo addparentcategory(
			ProductCategoryVo productCategoryVo) {

		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productCategoryResponseVo;

	}

	@Path("/addcategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
	public ProductCategoryResponseVo updateCategory(
			ProductCategoryVo productCategoryVo) {
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		ProductCategory productCategory = productCategoryService
				.getCategoryById(productCategoryVo.getProductCategoryId());
		if (productCategory != null) {
			productCategory.setName(productCategoryVo.getName());
			productCategoryService.updateCategory(productCategory);
			productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
		}
		return productCategoryResponseVo;

	}

	@Path("/removecategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProductCategoryResponseVo removeCategory(
			ProductCategoryVo productCategoryVo) {
		ProductCategoryResponseVo productCategoryResponseVo = new ProductCategoryResponseVo();
		ProductCategory productCategory = productCategoryService
				.getCategoryById(productCategoryVo.getProductCategoryId());
		if (productCategory != null) {
			productCategoryService.deleteCategory(productCategory);
			productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
		}
		return productCategoryResponseVo;
	}

	@Path("/getcategories")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
					.setProductCategoryVo(rootProductCategoryVoList);
			productCategoryResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
			// productCategoryResponseVo=productCategory.
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
				.setAuditColumnInfo(ProductCategory.class.getName());
		productCategory.setName(ProductCategoryVo.getName());
		merchant = merchantService.getMerchantById(ProductCategoryVo
				.getMerchant().getMerchantId());
		if (merchant != null) {
			productCategory.setMerchant(merchant);
		}
		return productCategory;

	}

}
