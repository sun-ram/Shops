package com.mitosis.shopsbacker.common.dao;

import com.mitosis.shopsbacker.model.Image;

public interface ImageDao<T> {
	
	public void deleteImage(Image image);

	public Image getImageById(String id);

	public void addImage(Image image);
}
