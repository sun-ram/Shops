package com.mitosis.aviate.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.CommonDao;
import com.mitosis.aviate.dao.ProductDao;
import com.mitosis.aviate.model.BinProductModel;
import com.mitosis.aviate.model.CustomerModel;
import com.mitosis.aviate.model.MyCartModel;
import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductImages;
import com.mitosis.aviate.model.ProductType;
import com.mitosis.aviate.model.SalesOrderLineModel;
import com.mitosis.aviate.model.SalesOrderModel;
import com.mitosis.aviate.model.ShippingChargeModel;
import com.mitosis.aviate.model.StoreModel;
import com.mitosis.aviate.model.TaxModel;
import com.mitosis.aviate.model.service.ProductTypeResponse;
import com.mitosis.aviate.util.AVMessageStatus;
import com.mitosis.aviate.webservice.ecommerce.ProductServices;

public class ProductDaoImpl extends BaseService implements ProductDao {
	final static Logger log = Logger
			.getLogger(ProductDaoImpl.class);
	CommonDao commonDao = new CommonDaoImpl(); 

	public List<StoreModel> getShopList(JSONObject filterDetails) throws Exception {
		List<StoreModel> listOfShops = null;
		try
		{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<StoreModel> cq = qb.createQuery(StoreModel.class);
			Root<StoreModel> sl = cq.from(StoreModel.class);
			cq.select(sl);
			if(commonDao.isValidProperty(filterDetails, "filterType") && !filterDetails.getString("filterType").equals("ALL")){

			}
			else if(commonDao.isValidProperty(filterDetails, "city") 
					|| commonDao.isValidProperty(filterDetails, "area")){

				if(commonDao.isValidProperty(filterDetails, "city") && commonDao.isValidProperty(filterDetails, "area")){
					Path city=sl.get("city");
					Predicate cityLike = qb.like(city, "%"+filterDetails.getString("city")+"%");
					Path area=sl.get("address");
					Predicate areaLike = qb.like(area, "%"+filterDetails.getString("area")+"%");
					if(commonDao.isValidProperty(filterDetails, "postalCode")){
					Path Postal=sl.get("postalCode");
					Predicate postalCodeLike = qb.like(Postal, "%"+filterDetails.getString("area")+"%");
					cq.where(cityLike, qb.or(areaLike, postalCodeLike));
					}else{
						cq.where(cityLike, areaLike);
					}
				}
				else if(commonDao.isValidProperty(filterDetails, "city")){
					Path city=sl.get("city");
					Predicate cityLike = qb.like(city, "%"+filterDetails.getString("city")+"%");
					cq.where(cityLike);
				}
				else if(commonDao.isValidProperty(filterDetails, "area")){
					Path area=sl.get("address");
					Predicate areaLike = qb.like(area, "%"+filterDetails.getString("area")+"%");
					Path Postal=sl.get("postalCode");
					Predicate postalCodeLike = qb.like(Postal, "%"+filterDetails.getString("area")+"%");
					cq.where(qb.or(areaLike ,postalCodeLike));
				}
			}
			cq.orderBy(qb.asc(sl.get("storeOrder")));
			listOfShops = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return listOfShops;
	}
	
	public List<String> getShopCityList(){
		try{
		begin();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> query = cb.createQuery(String.class);
		Root<StoreModel> store = query.from(StoreModel.class);
		query.select(store.<String>get("city"));
		TypedQuery<String> typedQuery = entityManager.createQuery(query);
		List<String> cityList = (List<String>)typedQuery.getResultList();
		return cityList;
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}
	
	@Override
	public List<ProductCategory> getCategoryList(Long merchantId) {
		List<ProductCategory> productCategoryList = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			Query query = entityManager.createQuery("select pc from ProductCategory pc where pc.merchantId = :merchantId and pc.parentCategoryId is null", ProductCategory.class);
			query.setParameter("merchantId", merchantId);
			productCategoryList = query.getResultList();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return productCategoryList;
	}

	@Override
	public List<ProductDetails> getProductList(Long productTypeId) {
		List<ProductDetails> productsList = new ArrayList<ProductDetails>();
		try{
			begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductDetails> cq = qb.createQuery(ProductDetails.class);
			Root<ProductDetails> productList = cq.from(ProductDetails.class);
			cq.select(productList);
			cq.where(qb.equal(productList.get("productTypeId"),productTypeId));
			productsList = entityManager.createQuery(cq).getResultList();

		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return productsList;
	}

	@Override
	public ProductDetails getProductDetails(Long productId) {
		ProductDetails product = new ProductDetails();
		try{
			begin();
			product = entityManager.find(ProductDetails.class, productId);
			flush();
			clear();
			commit();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return product;
	}

	public void getCategoryIds(List<ProductCategory> productCategoryList,List<Long> ids){
		for(ProductCategory productCategory:productCategoryList){
			ids.add(productCategory.getCategoryId());
			if(productCategory.getCategory().size()>0){
				getCategoryIds(productCategory.getCategory(),ids);
			}
		}
	}
	public void getLeafCategories(List<ProductCategory> productCategoryList, List<ProductCategory> productCategories){
		for(ProductCategory productCategory:productCategoryList){
			if(productCategory.getCategory().size()>0){
				getLeafCategories(productCategory.getCategory(), productCategories);
			}
			else{
				ProductCategory category = new ProductCategory();
				category.setCategoryId(productCategory.getCategoryId());
				category.setCategoryName(productCategory.getCategoryName());
				category.setStoreId(productCategory.getStoreId());
				productCategories.add(category);
			}
		}
	}


	public void getParentCategories(List<ProductCategory> productCategoryList, List<ProductCategory> productCategories){
		for(ProductCategory productCategory:productCategoryList){
			ProductCategory category = new ProductCategory();
			category.setCategoryId(productCategory.getCategoryId());
			category.setCategoryName(productCategory.getCategoryName());
			category.setParentCategoryId(productCategory.getParentCategoryId());
			productCategories.add(category);
			if(productCategory.getCategory().size()>0){
				getParentCategories(productCategory.getCategory(), productCategories);
			}
		}
	}

	@Override
	public List<ProductDetails> getAllProductListByCategory(Long categoryId) {
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		List<ProductDetails> productsList = new ArrayList<ProductDetails>();
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			Query query = entityManager.createQuery("select pc from ProductCategory pc where pc.categoryId= :categoryId");
			query.setParameter("categoryId", categoryId);
			productCategoryList = query.getResultList();

			List<Long> ids=new ArrayList<Long>();
			getCategoryIds(productCategoryList, ids);

			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductDetails> cq = qb.createQuery(ProductDetails.class);
			Root<ProductType> productSubcategory = cq.from(ProductType.class);
			Root<ProductDetails> productList = cq.from(ProductDetails.class);
			cq.select(productList);
			Expression<String> exp = productSubcategory.get("categoryId");
			Predicate predicate = exp.in(ids);
			cq.where(qb.equal(productSubcategory.get("productTypeId"),productList.get("productTypeId")), 
					predicate);
			productsList = entityManager.createQuery(cq).getResultList();

		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return productsList;
	}

	@Override
	public List<ProductCategory> getCategoryListById(Long categoryId) {
		List<ProductCategory> categoryList = new ArrayList<ProductCategory>();
		try{
			begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductCategory> cq = qb.createQuery(ProductCategory.class);
			Root<ProductCategory> productList = cq.from(ProductCategory.class);
			cq.select(productList);
			cq.where(qb.equal(productList.get("parentCategoryId"),categoryId));
			categoryList = entityManager.createQuery(cq).getResultList();

		}catch(Exception e){
			log.error(e.getMessage());
			throw e;

		}finally{
			close();
		}
		return categoryList;
	}

	@Override
	public void updateCart(MyCartModel myCart) {
		List<MyCartModel> getCart = new ArrayList<MyCartModel>();
		try{
			begin();
			/*Query query = createQuery("select e from MyCartModel e where e.customerId= :customerId "
					+ "and e.productId= :productId  and e.storeId= :storeId", MyCartModel.class);
			query.setParameter("customerId", myCart.getCustomerId());
			query.setParameter("productId", myCart.getProductId());
			query.setParameter("storeId", myCart.getStoreId());
			getCart = (MyCartModel) query.getSingleResult();*/
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<MyCartModel> cq = qb.createQuery(MyCartModel.class);
			Root<MyCartModel> cartValue = cq.from(MyCartModel.class);
			cq.select(cartValue);
			cq.where(qb.equal(cartValue.get("customerId"), myCart.getCustomerId()),
					qb.equal(cartValue.get("productId"), myCart.getProductId()),
					qb.equal(cartValue.get("storeId"), myCart.getStoreId()));
			getCart = entityManager.createQuery(cq).getResultList();
			if(!getCart.isEmpty()){
				myCart.setMyCartId(getCart.get(0).getMyCartId());
			}
			myCart.setActive(true);
			merge(myCart);
			commit();
		}catch(Exception e){
			log.error(e.getMessage());
		}finally{
			close();
		}
	}

	@Override
	public boolean removeFromCart(JSONObject reqObj) throws Exception {
		boolean flag = false;
		MyCartModel getCart = null;
		try{
			begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<MyCartModel> cq = qb.createQuery(MyCartModel.class);
			Root<MyCartModel> cartValue = cq.from(MyCartModel.class);
			cq.select(cartValue);
			if(commonDao.isValidProperty(reqObj, "myCartId")){
				cq.where(qb.equal(cartValue.get("myCartId"),reqObj.getString("myCartId")));
			}else if(commonDao.isValidProperty(reqObj, "productId")
					&& commonDao.isValidProperty(reqObj, "customerId")){
				cq.where(qb.equal(cartValue.get("customerId"),reqObj.getString("customerId"))
						,qb.equal(cartValue.get("productId"),reqObj.getString("productId"))
						,qb.equal(cartValue.get("storeId"),reqObj.getString("storeId")));
			}
			getCart = entityManager.createQuery(cq).getSingleResult();
			getCart.setActive(false);
			merge(getCart);
			commit();
			flag = true;
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}





	@Override
	public boolean deleteFromCart(JSONObject reqObj) throws Exception {
		boolean flag = false;
		try{
			begin();
			Query query = createQuery("delete from MyCartModel c where c.customerId= :customerId and c.storeId= :storeId", MyCartModel.class);
			query.setParameter("customerId", Integer.parseInt(reqObj.getString("customerId")));
			query.setParameter("storeId", Integer.parseInt(reqObj.getString("storeId")));
			query.executeUpdate();
			commit();
			flag = true;
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public List<MyCartModel> getMyCartList(int customerId, Long storeId) {
		List<MyCartModel> getCart = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<MyCartModel> cq = qb.createQuery(MyCartModel.class);
			Root<MyCartModel> cartValue = cq.from(MyCartModel.class);
			cq.select(cartValue);
			cq.where(qb.equal(cartValue.get("customerId"),customerId)
					,qb.equal(cartValue.get("active"),true)
					,qb.equal(cartValue.get("storeId"),storeId));
			getCart = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return getCart;
	}
	
	@Override
	public List<ProductDetails> SearchProductResponse(Long storeId,String searchString) {
		
		List<ProductDetails> getProductDetails = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();			
			CriteriaQuery<ProductDetails> cq = qb.createQuery(ProductDetails.class);
			Root<ProductDetails> productValue = cq.from(ProductDetails.class);
			cq.select(productValue);
			Path productName=productValue.get("productName");
			Predicate productNameLike = qb.like(productName, "%"+searchString+"%");
			cq.where(qb.equal(productValue.get("storeId"),storeId),productNameLike);
			getProductDetails = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return getProductDetails;
	
	}
	
	@Override
	public List<ProductCategory> SearchProductCategoryResponse(Long storeId,String searchString) {
		
		List<ProductCategory> getProductCategory = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();			
			CriteriaQuery<ProductCategory> cq = qb.createQuery(ProductCategory.class);			
			Root<ProductCategory> productValue = cq.from(ProductCategory.class);
			cq.select(productValue);
			Path categoryName=productValue.get("categoryName");
			Predicate categoryNameLike = qb.like(categoryName, "%"+searchString+"%");
			cq.where(qb.equal(productValue.get("storeId"),storeId),categoryNameLike);
			getProductCategory = entityManager.createQuery(cq).getResultList();			
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return getProductCategory;
	}
	
	@Override
	public List<ProductType> SearchProductTypeResponse(Long storeId,String searchString) {
		
		List<ProductType> getProductType = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();			
			CriteriaQuery<ProductType> cq = qb.createQuery(ProductType.class);			
			Root<ProductType> productValue = cq.from(ProductType.class);
			cq.select(productValue);
			Path productTypeName=productValue.get("productTypeName");
			Predicate typeNameLike = qb.like(productTypeName, "%"+searchString+"%");
			cq.where(qb.equal(productValue.get("storeId"),storeId),typeNameLike);
			getProductType = entityManager.createQuery(cq).getResultList();		
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return getProductType;
	}

	@Override
	public List<TaxModel> getShopTax(JSONObject storeId) throws Exception {
		List<TaxModel> taxModel = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();			
			CriteriaQuery<TaxModel> cq = qb.createQuery(TaxModel.class);			
			Root<TaxModel> tax = cq.from(TaxModel.class);
			cq.select(tax);
			//cq.where(qb.equal(tax.get("storeId"),Long.parseLong(storeId.getString("storeId"))));
			taxModel = entityManager.createQuery(cq).getResultList();			
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return taxModel;
	}

	@Override
	public List<ShippingChargeModel> getShippingCharges(Long storeId) {
		List<ShippingChargeModel> shippingChargesList = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();			
			CriteriaQuery<ShippingChargeModel> cq = qb.createQuery(ShippingChargeModel.class);			
			Root<ShippingChargeModel> tax = cq.from(ShippingChargeModel.class);
			cq.select(tax);
			//cq.where(qb.equal(tax.get("storeId"),storeId));
			shippingChargesList = entityManager.createQuery(cq).getResultList();			
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return shippingChargesList;
	}

	@Override
	public List<ProductDetails> getMerchantProducts(Long merchantId) {
		List<ProductDetails> products = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();			
			CriteriaQuery<ProductDetails> cq = qb.createQuery(ProductDetails.class);			
			Root<ProductDetails> product = cq.from(ProductDetails.class);
			cq.select(product);
			cq.where(qb.equal(product.get("merchantId"),merchantId));
			products = entityManager.createQuery(cq).getResultList();			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return products;
	}
	
	@Override
	public boolean deleteProductById(long productId) {

		boolean flag = false;
		try{
			begin();
			ProductDetails productModel;
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductDetails> cq = qb.createQuery(ProductDetails.class);
			Root<ProductDetails> storeobj = cq.from(ProductDetails.class);
			cq.select(storeobj);
			cq.where(qb.equal(storeobj.get("productId"),productId));
			productModel = entityManager.createQuery(cq).getSingleResult();
			remove(productModel);
			flush();
			clear();
			commit();
			flag = true;
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public List<ProductDetails> getFutureProducts(long merchantId) {
		List<ProductDetails> productDetails = new ArrayList<ProductDetails>();
		try{
			begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductDetails> cq = qb.createQuery(ProductDetails.class);
			Root<ProductDetails> storeobj = cq.from(ProductDetails.class);
			Root<BinProductModel> binProduct = cq.from(BinProductModel.class);
			cq.select(storeobj);
			cq.where(qb.equal(storeobj.get("merchantId"),merchantId), 
					qb.notEqual(storeobj.get("productId"),binProduct.get("productId")),
					qb.equal(binProduct.get("merchantId"),merchantId));
			productDetails = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return productDetails;
	}

	@Override
	public List<ProductDetails> getTopCategories(Long merchantId) {
		List<ProductDetails> productDetails = new ArrayList<ProductDetails>();
		try{
			begin();
			
		/*	entityManager.getEntityManagerFactory().getCache().evictAll();
			Query query = entityManager.createQuery("SELECT pd from SalesOrderLineModel sol ,ProductDetails pd where sol.productId = pd.productId and sales_order_id in (select SM.salesOrderId  from SalesOrderModel SM where SM.merchantId = :merchantId) group by product_id order by sum(quantity) desc", ProductDetails.class);
			query.setParameter("merchantId", merchantId);
			productDetails = query.getResultList();*/
			
			
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductDetails> cq = qb.createQuery(ProductDetails.class);
			Root<SalesOrderLineModel> solm = cq.from(SalesOrderLineModel.class);
			Root<ProductDetails> pd = cq.from(ProductDetails.class);
			Root<SalesOrderModel> som= cq.from(SalesOrderModel.class);
			cq.select(pd);
			cq.where(qb.equal(pd.get("merchantId"),merchantId)/*,
					qb.equal(solm.get("salesOrderId"),som.get("salesOrderId")),
					qb.equal(pd.get("productId"),solm.get("productId"))*/
					);
			
			productDetails = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return productDetails;
	}
	
	@Override
	public List<ProductCategory> getPoductCategoryList(Long merchantId) {
		List<ProductCategory> products = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();			
			CriteriaQuery<ProductCategory> cq = qb.createQuery(ProductCategory.class);			
			Root<ProductCategory> product = cq.from(ProductCategory.class);
			cq.select(product);
			cq.where(qb.equal(product.get("merchantId"),merchantId));
			products = entityManager.createQuery(cq).getResultList();			
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return products;
	}
	
	@Override
	public List<ProductImages> getProductImage(Long productId) {
		List<ProductImages> images = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();			
			CriteriaQuery<ProductImages> cq = qb.createQuery(ProductImages.class);			
			Root<ProductImages> product = cq.from(ProductImages.class);
			cq.select(product);
			cq.where(qb.equal(product.get("productId"),productId));
			images = entityManager.createQuery(cq).getResultList();	
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return images;
	}
	
	@Override
	public boolean updateProductImage(ProductImages images) {
		boolean flag =false;
		try{
			begin();
			merge(images);
			flush();
			clear();
			commit();
			flag=true;
		}catch(Exception e){
			log.error(e.getMessage());
			throw e;
		}
		return flag;
	}
}