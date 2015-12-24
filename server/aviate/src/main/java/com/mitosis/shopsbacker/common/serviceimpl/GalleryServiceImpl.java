/**
 * 
 */
package com.mitosis.shopsbacker.common.serviceimpl;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.common.dao.GalleryDao;
import com.mitosis.shopsbacker.common.service.GalleryService;
import com.mitosis.shopsbacker.model.Gallery;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Service("galleryServiceImpl")
public class GalleryServiceImpl implements GalleryService<T> {
	
	@Autowired
	GalleryDao<T> galleryDao;

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.service.GalleryService#deleteGallery(com.mitosis.shopsbacker.model.Gallery)
	 */
	@Override
	public void deleteGallery(Gallery gallery) {
		galleryDao.deleteGallery(gallery);
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.service.GalleryService#getGalleryById(java.lang.String)
	 */
	@Override
	public Gallery getGalleryById(String id) {
		 
		return galleryDao.getGalleryById(id);
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.service.GalleryService#addImage(com.mitosis.shopsbacker.model.Gallery)
	 */
	@Override
	public void addGallery(Gallery gallery) {
		galleryDao.addGallery(gallery);
	}

	/* (non-Javadoc)
	 * @see com.mitosis.shopsbacker.common.service.GalleryService#getImages(java.util.List)   
	 */
	@Override
	public List<Gallery> getGalleries() {
		return galleryDao.getGalleries();
	}

}
