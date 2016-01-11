package com.mitosis.shopsbacker.vo.admin;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.common.GalleryVo;

public class GalleryResponse extends ResponseModel{
	
	GalleryVo gallery;

	public GalleryVo getGallery() {
		return gallery;
	}

	public void setGallery(GalleryVo gallery) {
		this.gallery = gallery;
	}
}
