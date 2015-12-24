/**
 * 
 */
package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.common.GalleryVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
public class GalleryResponseVo extends ResponseModel {
	public List<GalleryVo> galleries =new ArrayList<GalleryVo>();

	public List<GalleryVo> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<GalleryVo> galleries) {
		this.galleries = galleries;
	}
	
}
