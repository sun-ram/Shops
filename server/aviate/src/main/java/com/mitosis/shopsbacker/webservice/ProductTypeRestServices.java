package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.inventory.service.ProductCategoryService;
import com.mitosis.shopsbacker.inventory.service.ProductTypeService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.ProductCategory;
import com.mitosis.shopsbacker.model.ProductType;
import com.mitosis.shopsbacker.responsevo.ProductTypeResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBErrorMessage;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.inventory.ProductTypeVo;

@Path("producttype")
@Controller("productTypeRestServices")
public class ProductTypeRestServices<T> {

	@Autowired
	ProductTypeService<T> productTypeService;

	@Autowired
	ProductCategoryService<T> productCategoryService;

	@Autowired(required = true)
	MerchantService<T> merchantService;

	@Path("/addproducttypes")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ProductTypeResponseVo addProductType(ProductTypeVo productTypeVo)
			throws Exception {
		ProductTypeResponseVo productTypeResponseVo = new ProductTypeResponseVo();
		ProductType productType = new ProductType();
		ProductCategory productCategory = productCategoryService
				.getCategoryById(productTypeVo.getProductCategory()
						.getProductCategoryId());
		boolean flag=productTypeService.checkProductTypeWithname(productCategory,productTypeVo.getName());
		if(flag){
		productType = setProductTypeDetails(productTypeVo);
		productType.setName(productTypeVo.getName());
		productType.setProductCategory(productCategory);
		productType.setIsactive('Y');
		productTypeService.addProductType(productType);
		if (productType.getProductTypeId() == null) {
			productTypeResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
		} else {
			productTypeResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		}
		return productTypeResponseVo;
		}
		else{
			productTypeResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			productTypeResponseVo.setErrorCode(SBErrorMessage.PRODUCTTYPE_EXIST.getCode());
			productTypeResponseVo.setErrorString(SBErrorMessage.PRODUCTTYPE_EXIST.getMessage());
			return productTypeResponseVo;
		}
	}

	@Path("/updateproducttypes")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ProductTypeResponseVo updateProductType(ProductTypeVo productTypeVo) {
		ProductTypeResponseVo productTypeResponseVo = new ProductTypeResponseVo();
		ProductType productType = productTypeService
				.getProductTypeById(productTypeVo.getProductTypeId());
		if (productType != null) {
			productType.setName(productTypeVo.getName());
			productTypeService.updateProductType(productType);
			productTypeResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		} else {
			productTypeResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return productTypeResponseVo;

	}

	@Path("/removeproducttype")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ProductTypeResponseVo removeproducttype(ProductTypeVo productTypeVo) {
		ProductTypeResponseVo productTypeResponseVo = new ProductTypeResponseVo();
		ProductType productType = productTypeService
				.getProductTypeById(productTypeVo.getProductTypeId());
		if (productType != null) {
			productTypeService.removeProductType(productType);
			productTypeResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		}
		return productTypeResponseVo;
	}

	@Path("/getproducttypesbystore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ProductTypeResponseVo getProductTypeByStore(
			ProductTypeVo productTypeVo) {
		ProductTypeResponseVo productTypeResponseVo = new ProductTypeResponseVo();
		Merchant merchant = merchantService.getMerchantById(productTypeVo
				.getMerchant().getMerchantId());
		
		if (merchant == null) {
			return productTypeResponseVo;
		}
			List<ProductTypeVo> productTypeVoResponse = productTypeService.prepareProductTypeVoList(merchant);
			productTypeResponseVo.setProductTypeVo(productTypeVoResponse);
		 

		return productTypeResponseVo;

	}
	


	@Path("/getProductTypeByCategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ProductTypeResponseVo getProductTypeByCategory(
			ProductTypeVo productTypeVo) {
		ProductTypeResponseVo productTypeResponseVo = new ProductTypeResponseVo();
		List<ProductTypeVo> productTypeVoResponse = new ArrayList<ProductTypeVo>();
		List<ProductType> productType = new ArrayList<ProductType>();
		ProductCategory productCategory = new ProductCategory();
		productCategory = productCategoryService.getCategoryById(productTypeVo
				.getProductCategory().getProductCategoryId());
		if (productCategory != null) {
			productType = productTypeService
					.getProductTypeByCategory(productCategory);
			for (ProductType productTypeSet : productType) {
				ProductTypeVo productTypeVoSet = new ProductTypeVo();
				productTypeVoSet.setProductTypeId(productTypeSet
						.getProductTypeId());
				productTypeVoSet.setName(productTypeSet.getName());
				productTypeVoResponse.add(productTypeVoSet);
			}
			productTypeResponseVo.setProductTypeVo(productTypeVoResponse);
			productTypeResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		}
		return productTypeResponseVo;
	}

	public ProductType setProductTypeDetails(ProductTypeVo ProductTypeVo)
			throws Exception {
		Merchant merchant = new Merchant();
		ProductType productType = (ProductType) CommonUtil
				.setAuditColumnInfo(ProductType.class.getName());
		merchant = merchantService.getMerchantById(ProductTypeVo.getMerchant()
				.getMerchantId());
		if (merchant != null) {
			productType.setMerchant(merchant);
		}
		return productType;

	}

}
