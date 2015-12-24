/**
 * 
 */
package com.mitosis.shopsbacker.common.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.dao.GalleryDao;
import com.mitosis.shopsbacker.model.Gallery;
import com.mitosis.shopsbacker.model.Image;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Repository
public class GalleryDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
		Serializable, GalleryDao<T> {

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.dao.GalleryDao#deleteGallery(com.mitosis.shopsbacker.model.Gallery)
	 */
	@Override
	public void deleteGallery(Gallery gallery) {
		delete((T)gallery);
		
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.dao.GalleryDao#getGalleryById(java.lang.String)
	 */
	@Override
	public Gallery getGalleryById(String id) {
		return (Gallery) getSession().get(Gallery.class, id);
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.dao.GalleryDao#addImage(com.mitosis.shopsbacker.model.Gallery)
	 */
	@Override
	public void addGallery(Gallery gallery) {
		save((T) gallery);
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.dao.GalleryDao#getImages(java.util.List)
	 */
	@Override
	public List<Gallery> getGalleries() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Image.class);
		criteria.add(Restrictions.eq("isactive",'Y'));
		return (List<Gallery>) findAll(criteria);
	}

}
