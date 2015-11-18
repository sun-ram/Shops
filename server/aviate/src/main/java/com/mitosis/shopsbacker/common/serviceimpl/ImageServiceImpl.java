package com.mitosis.shopsbacker.common.serviceimpl;

import java.io.IOException;
import java.util.Properties;

import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;

public class ImageServiceImpl<T> implements ImageService<T> {

	@Override
	public ImageVo setImageVo(Merchant merchant) throws IOException {
		ImageVo imageVo = new ImageVo();
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		String imageUrl = properties.getProperty("imageUrl");
		imageVo.setName(merchant.getLogo().getName());
		imageVo.setType(merchant.getLogo().getType());
		imageVo.setUrl(imageUrl.concat(merchant.getLogo().getUrl()));
		return imageVo;
	}

	@Override
	public Image setImage(MerchantVo merchantVo) throws Exception {
		Image image = (Image) CommonUtil.setAuditColumnInfo(Image.class.getName());
		image.setName(merchantVo.getLogo().getName());
		image.setType(merchantVo.getLogo().getType());
		image.setUrl(merchantVo.getLogo().getUrl());
		return image;
	}



}
