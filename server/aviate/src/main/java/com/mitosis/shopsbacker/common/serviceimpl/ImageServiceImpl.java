package com.mitosis.shopsbacker.common.serviceimpl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.admin.dao.BannerDao;
import com.mitosis.shopsbacker.common.dao.ImageDao;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.common.ImageVo;

@Service("imageServiceImpl")
public class ImageServiceImpl<T> implements ImageService<T> {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	ImageDao<T> imageDao;

	public ImageDao<T> getImageDao() {
		return imageDao;
	}

	public void setImageDao(ImageDao<T> imageDao) {
		this.imageDao = imageDao;
	}

	@Override
	public ImageVo setImageVo(Merchant merchant) throws IOException {
		ImageVo imageVo = new ImageVo();
		Image logo = merchant.getLogo();
		String imageUrl = generateMerchantImageUrl(logo.getUrl());
		imageVo.setName(logo.getName());
		imageVo.setType(logo.getType());
		imageVo.setUrl(imageUrl);
		return imageVo;
	}

	
	@Override
	public Image setImage(ImageVo imageVo) throws Exception {


		Image image = (Image) CommonUtil
				.setAuditColumnInfo(Image.class.getName());
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
		boolean isDeleted=CommonUtil.removeImage(defaultImagePath.concat(image.getUrl()));
		if(isDeleted){
			imageDao.deleteImage(image);
		}
		return isDeleted;
	}
	

	@Override
	public ImageVo setImageVo(Image image) throws IOException {
		ImageVo imageVo = new ImageVo();
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		String imageUrl = properties.getProperty("imageUrl");
		imageVo.setImageId(image.getImageId());
		imageVo.setName(image.getName());
		imageVo.setType(image.getType());
		imageVo.setUrl(imageUrl.concat(image.getUrl()));
		return imageVo;
	}
	
	@Override
	public Image getImageById(String id) throws Exception {
	
		Image image = imageDao.getImageById(id);
		
		return image;
	}
	
	public void addImage(Image image){
		imageDao.addImage(image);
	}

	/**
	 * @author Anbukkani Gajendran
	 * @param merchant
	 * @return String (Image URL as string)
	 * @throws IOException
	 */
	public String generateMerchantImageUrl(String url)
			throws IOException {
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		String imageUrl = properties.getProperty("imageUrl");
		imageUrl=imageUrl.concat(url);
		return imageUrl;
	}
	
}
