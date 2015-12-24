/**
 * 
 */
package com.mitosis.shopsbacker.common.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Gallery;

/**
 * @author Anbukkani Gajendiran
 *
 */
public interface GalleryDao<T> {

	public void deleteGallery(Gallery gallery);

	public Gallery getGalleryById(String id);

	public void addGallery(Gallery gallery);
	
	public List<Gallery> getGalleries();
}
