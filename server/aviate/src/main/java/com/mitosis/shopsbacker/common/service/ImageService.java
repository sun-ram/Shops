package com.mitosis.shopsbacker.common.service;

import java.io.IOException;

import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;

public interface ImageService<T>  {
	
	
	public ImageVo setImageVo(Merchant merchant) throws IOException;
	
	public Image setImage(MerchantVo merchantVo) throws Exception;

	
	

}
