package com.mitosis.shopsbacker.admin.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.mitosis.shopsbacker.admin.dao.BannerDao;
import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.model.Banner;
import com.mitosis.shopsbacker.model.Store;

@Repository
public class BannerDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
BannerDao<T>, Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void saveBanner(Banner banner) {
		save((T) banner);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateBanner(Banner banner) {
		try {
			update((T) banner);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Banner> getBannerListByStore(Store store) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Banner.class);
		criteria.add(Restrictions.eq("store", store));
		return (List<Banner>) findAll(criteria);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Banner> getBannerListByFlag(char isShopsbackerBanner) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Banner.class);
		criteria.add(Restrictions.eq("isShopsbackerBanner", isShopsbackerBanner));
		return (List<Banner>) findAll(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteBanner(Banner banner) {
		delete((T) banner);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Banner getBannerById(String id) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Banner.class);
		criteria.add(Restrictions.eq("bannerId", id));
		return ((List<Banner>) findAll(criteria)).get(0);

	}

}
