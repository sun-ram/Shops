package com.mitosis.shopsbacker.common.daoimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.dao.ImageDao;
import com.mitosis.shopsbacker.model.Image;

/**
 * @author kathir
 *
 * @param <T>
 */
@Repository
@Transactional
public class ImageDaoImpl<T> extends CustomHibernateDaoSupport<T> implements
ImageDao<T>, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void deleteImage(Image image) {
		delete((T) image);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Image getImageById(String id) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Image.class);
		criteria.add(Restrictions.eq("imageId", id));
		return ((List<Image>) findAll(criteria)).get(0);

	}
	

}
