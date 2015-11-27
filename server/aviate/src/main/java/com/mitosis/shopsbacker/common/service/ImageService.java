package com.mitosis.shopsbacker.common.service;

import java.io.IOException;
import java.util.List;

import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.vo.common.ImageVo;

public interface ImageService<T>  {
	
	
	public ImageVo setImageVo(Merchant merchant) throws IOException;
	
	public Image setImage(ImageVo imageVo) throws Exception;

	public boolean deleteImage(Image image) throws IOException,
	Exception ;
	
	public Image getImageById(String id) throws Exception;
	
	public ImageVo setImageVo(Image image) throws IOException;

	public void addImage(Image image);
	
	public String generateMerchantImageUrl(String url)
			throws IOException;
	
	public List<Image> getImages(List<String> ids) throws Exception;
}
