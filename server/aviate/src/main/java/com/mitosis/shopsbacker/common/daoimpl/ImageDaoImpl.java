package com.mitosis.shopsbacker.common.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mitosis.shopsbacker.common.dao.ImageDao;
import com.mitosis.shopsbacker.model.Image;

/**
 * @author kathir
 *
 * @param <T>
 * 
 * Reviewed by Sundaram 28/11/2015
 */
@Repository
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
		 Image  image= null;
		 List<Image> images= (List<Image>) findAll(criteria);
		if( images.size()>0){
			image= images.get(0);
		}
		return image ;

	}
	
		public void addImage(Image image){
				save((T)image);
			}
		
		public List<Image> getImages(List<String> ids){
			DetachedCriteria criteria = DetachedCriteria.forClass(Image.class);
			criteria.add(Restrictions.in("imageId",ids));
			criteria.add(Restrictions.eq("isactive",'Y'));
			return (List<Image>) findAll(criteria);
		}

}
