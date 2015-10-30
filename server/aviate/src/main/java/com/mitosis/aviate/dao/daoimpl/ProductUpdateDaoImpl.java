package com.mitosis.aviate.dao.daoimpl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.dao.ProductUpdateDao;
import com.mitosis.aviate.model.ProductCategory;
import com.mitosis.aviate.model.ProductDetails;
import com.mitosis.aviate.model.ProductImages;
import com.mitosis.aviate.model.ProductOffer;
import com.mitosis.aviate.model.ProductPriceDetails;
import com.mitosis.aviate.model.ProductType;
import com.mitosis.aviate.model.ProductUOM;
import com.mitosis.aviate.model.Units;
import com.mitosis.aviate.util.AVMessageStatus;

public class ProductUpdateDaoImpl extends BaseService implements ProductUpdateDao {
	final static Logger log = Logger.getLogger(ProductUpdateDaoImpl.class);
	
	@Override
	public List<ProductCategory> getCategoryByParentId(Long parentCategoryId) {
		List<ProductCategory> categories = new ArrayList<ProductCategory>();
		try{
			begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductCategory> cq = qb.createQuery(ProductCategory.class);
			Root<ProductCategory> pc = cq.from(ProductCategory.class);
			cq.select(pc);
			cq.where(qb.equal(pc.get("parentCategoryId"),parentCategoryId));
			categories=entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return categories;
	}

	@Override
	public boolean addCategory(ProductCategory productCategory) {
		List<ProductCategory> categories = new ArrayList<ProductCategory>();
		try{
			begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductCategory> cq = qb.createQuery(ProductCategory.class);
			Root<ProductCategory> pc = cq.from(ProductCategory.class);
			cq.select(pc);
			Expression<String> exp = pc.get("parentCategoryId");
			Predicate predicate = exp.isNull();
			cq.where(qb.equal(pc.get("categoryName"),productCategory.getCategoryName()),
					qb.equal(pc.get("storeId"),productCategory.getStoreId()),predicate);
			categories=entityManager.createQuery(cq).getResultList();
			if(categories.isEmpty()){
				persist(productCategory);
				commit();
			} else{
				return false;
			}                                  
		}catch(Exception e){
			if(entityManager!=null){
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return true;
	}

	@Override
	public ProductCategory selectCategoryById(ProductCategory selectCategory) {
		try{
			begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductCategory> cq = qb.createQuery(ProductCategory.class);
			Root<ProductCategory> pc = cq.from(ProductCategory.class);
			cq.select(pc);
			cq.where(qb.equal(pc.get("categoryId"),selectCategory.getCategoryId()));
			selectCategory=entityManager.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return selectCategory;
	}

	@Override
	public void updateCategory(ProductCategory selectCategory) {
		try{
			begin();
			merge(selectCategory);
			flush();
			clear();
			commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public void addProductType(ProductType productType) {
		try{
			begin();
			//TODO: Need to remove commanded code after testing. 
//			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
//			CriteriaQuery<ProductType> cq = qb.createQuery(ProductType.class);
//			Root<ProductType> pc = cq.from(ProductType.class);
//			cq.select(pc);
//			cq.where(qb.equal(pc.get("productTypeName"),productType.getProductTypeName()),qb.equal(pc.get("categoryId"),productType.getCategoryId()));
//			List<ProductType> categoryList=entityManager.createQuery(cq).getResultList();
				persist(productType);
				flush();
				clear();
				commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public boolean updateProductType(ProductType productType) {
		boolean flag = false;
		try{
			begin();
			flush();
			clear();
			merge(productType);
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public void updateProduct(ProductDetails productDetails) {

		try{
			begin();
			if(productDetails.getProductId()==null){

				persist(productDetails);
			}
			else {

				merge(productDetails);

			}

			flush();
			commit();
			productDetails.setStatus(AVMessageStatus.SUCCESS.getValue());

		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public void updateProductPrice(ProductPriceDetails productPrice) {
		try{
			begin();
			if(productPrice.getPriceId() == null){
				
				persist(productPrice);
				
			}
			else{
				
				merge(productPrice);
				
			}
			flush();
			clear();
			commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	public ProductImages getProductImageByImageName(String imageName) {
		ProductImages productImage = null;
		try{
			//begin();
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("aviatewebservice");
			entityManager = factory.createEntityManager();
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductImages> cq = qb.createQuery(ProductImages.class);
			Root<ProductImages> pi = cq.from(ProductImages.class);
			cq.select(pi);
			cq.where(qb.equal(pi.get("imageName"),imageName));
			productImage=entityManager.createQuery(cq).getSingleResult();
			//flush();
			//clear();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return productImage;
	}

	@Override
	public List<ProductType> getProductType(List<Long> categoryIds) {
		List<ProductType> productTypeList = null;
		try{
			begin();
			entityManager.getEntityManagerFactory().getCache().evictAll();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductType> cq = qb.createQuery(ProductType.class);
			Root<ProductType> sl = cq.from(ProductType.class);
			cq.select(sl);
			Expression<String> exp = sl.get("categoryId");
			Predicate predicate = exp.in(categoryIds);
			cq.where(predicate);
			productTypeList=entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return productTypeList;
	}

	@Override
	public ProductType getSingleProductType(long productTypeId) {
		ProductType productType = null;
		try{
			begin();
			   productType  = entityManager.find(ProductType.class, productTypeId);
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return productType;
	}

	@Override
	public boolean removeSingleProductType(long productTypeId) {

		boolean flag = false;
		try{
			begin();
			ProductType prouctType = null;
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductType> cq = qb.createQuery(ProductType.class);
			Root<ProductType> storeobj = cq.from(ProductType.class);
			cq.select(storeobj);
			cq.where(qb.equal(storeobj.get("productTypeId"),productTypeId));
			prouctType = entityManager.createQuery(cq).getSingleResult();
			remove(prouctType);
			flush();
			clear();
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public void updateProductOffer(ProductOffer productOffer) {
		List<ProductOffer> po;
		try{
			begin();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductOffer> cq = cb.createQuery(ProductOffer.class);
			Root<ProductOffer> offer = cq.from(ProductOffer.class);
			cq.select(offer);
			cq.where(cb.equal(offer.get("storeId"), productOffer.getStoreId()),
					cb.equal(offer.get("offerName"), productOffer.getOfferName()));
			po = entityManager.createQuery(cq).getResultList();
			if(po.isEmpty())
			{
				clear();
				flush();
				persist(productOffer);
				commit();
			}else{
				for(ProductOffer p : po){
					productOffer.setProductOfferId(p.getProductOfferId());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public void updateUOM(ProductUOM productUnitOfMeasure) {
		System.out.println(productUnitOfMeasure);
		try{
			begin();
			clear();
			flush();

			if(productUnitOfMeasure.getUnitOfMeasureId()==null){

				persist(productUnitOfMeasure);
			}
			else {

				merge(productUnitOfMeasure);

			}

			commit();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
	}

	@Override
	public boolean updateUnits(Units units) {
		boolean flag = false; 
		try{
			begin();
			flush();
			merge(units);
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return flag;
	}

	@Override
	public List<Units> getUnits() {
		List<Units> unitsList = null;
		try{
			begin();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Units> cq = cb.createQuery(Units.class);
			Root<Units> units = cq.from(Units.class);
			cq.select(units);
			unitsList = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return unitsList;
	}
	
	@Override
	public List<Units> getUnitsListByMerchant(JSONObject request){
		List<Units> unitsList = null;
		try{
			begin();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Units> cq = cb.createQuery(Units.class);
			Root<Units> units = cq.from(Units.class);
			cq.select(units);
			cq.where(cb.equal(units.get("merchantId"),request.getInt("merchantId")));
			unitsList = entityManager.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}finally{
			close();
		}
		return unitsList;
	}
	
	@Override
	public boolean deleteUnit(Long unitsId) {
		boolean flag = false;
		Units unit = null;
		try{
			begin();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Units> cq = cb.createQuery(Units.class);
			Root<Units> units = cq.from(Units.class);
			cq.select(units);
			cq.where(cb.equal(units.get("unitsId"),unitsId));
			unit = entityManager.createQuery(cq).getSingleResult();
			remove(unit);
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;		
	}

	@Override
	public boolean removeCategory(ProductCategory productCategory) {
		boolean flag = false;
		try{
			begin();
			CriteriaBuilder qb = entityManager.getCriteriaBuilder();
			CriteriaQuery<ProductCategory> cq = qb.createQuery(ProductCategory.class);
			Root<ProductCategory> pc = cq.from(ProductCategory.class);
			cq.select(pc);
			cq.where(qb.equal(pc.get("categoryId"),productCategory.getCategoryId()));
			productCategory=entityManager.createQuery(cq).getSingleResult();
			productCategory.setParentCategory(null);
			remove(productCategory);
			commit();
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}finally{
			close();
		}
		return flag;
	}
}
