package com.mitosis.shopsbacker.common.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Gallery;

/**
 * @author Anbukkani Gajendiran
 *
 */
public interface GalleryService<T> {

	public void deleteGallery(Gallery gallery);

	public Gallery getGalleryById(String id);

	public void addGallery(Gallery gallery);

	public List<Gallery> getGalleries();
	
	 public  List<Gallery> getRootGalleries();
}
