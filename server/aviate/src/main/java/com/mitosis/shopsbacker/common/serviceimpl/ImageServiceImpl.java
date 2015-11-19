package com.mitosis.shopsbacker.common.serviceimpl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.common.ImageVo;

@Service("imageServiceImpl")
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
	public Image setImage(ImageVo imageVo) throws Exception {
		Image image = null;
		if (imageVo.getImageId() == null) {
			image = (Image) CommonUtil
					.setAuditColumnInfo(Image.class.getName());
		} else {
			// TODO: Need to implement image dao and get image using given image
			// id. Then assign;
			image = null;
			image.setUpdated(new Date());
			// TODO: Need to get user from seesion and set to updated by.
			image.setUpdatedby("1234");
		}
		image.setName(imageVo.getName());
		image.setType(imageVo.getType());
		image.setUrl(imageVo.getUrl());
		return image;
	}

	public boolean deleteImage(Image image) throws IOException, Exception {
		String defaultImagePath = "";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		defaultImagePath = properties.getProperty("imagePath");
		return CommonUtil.removeImage(defaultImagePath.concat(image.getUrl()));
	}

}
