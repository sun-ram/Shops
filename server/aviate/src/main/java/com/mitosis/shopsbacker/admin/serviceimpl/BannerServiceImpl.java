package com.mitosis.shopsbacker.admin.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.BannerDao;
import com.mitosis.shopsbacker.admin.service.BannerService;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Banner;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.BannerVo;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;

@Service("bannerServiceImpl")
public class BannerServiceImpl<T> implements BannerService<T> {

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	ImageService<T> imageService;

	@Autowired
	BannerDao<T> bannerDao;

	BannerVo bannerVo = null;
	ImageVo imageVo = null;
	StoreVo storeVo = null;
	MerchantVo merchantVo = null;

	public BannerDao<T> getBannerDao() {
		return bannerDao;
	}

	public void setBannerDao(BannerDao<T> bannerDao) {
		this.bannerDao = bannerDao;
	}

	@Override
	@Transactional
	public void saveBanner(Banner banner) {
		getBannerDao().saveBanner(banner);
	}

	@Override
	@Transactional
	public void updateBanner(Banner banner) {
		getBannerDao().updateBanner(banner);
	}

	@Override
	@Transactional
	public List<Banner> getBannerList(Store store) {
		return getBannerDao().getBannerList(store);
	}

	@Override
	@Transactional
	public void deleteBanner(String id) throws Exception {
		Banner banner = bannerDao.getBannerById(id);
		Image image = imageService.getImageById(banner.getImage().getImageId());
		getBannerDao().deleteBanner(banner);
		imageService.deleteImage(image);
	}

	@Override
	@Transactional
	public Banner getBannerById(String id) {
		return getBannerDao().getBannerById(id);
	}

	@Override
	@Transactional
	public Banner setBanner(BannerVo bannerVo) throws Exception {
		Banner banner = null;
		Image img = null;

		if (bannerVo.getBannerId() == null) {
			banner = (Banner) CommonUtil.setAuditColumnInfo(Banner.class
					.getName());
			banner.setIsactive('Y');
			banner.setIsShopsbackerBanner('N');
			banner.setTabTitleBold(bannerVo.getTabTitleBold());
			banner.setTabTitleSmall(bannerVo.getTabTitleSmall());
		} else {
			banner = bannerDao.getBannerById(bannerVo.getBannerId());
			banner.setUpdated(new Date());
			banner.setUpdatedby("1234");
			banner.setTabTitleBold(bannerVo.getTabTitleBold());
			banner.setTabTitleSmall(bannerVo.getTabTitleSmall());

			if (bannerVo.getImage().getImage() != null
					&& bannerVo.getImage().getType() != null) {
				img = banner.getImage();
				imageService.deleteImage(img);
			}
		}

		banner.setMerchant(merchantService.getMerchantById(bannerVo
				.getMerchant().getMerchantId()));
		banner.setStore(storeService.getStoreById(bannerVo.getStore()
				.getStoreId()));

		if (bannerVo.getImage().getImage() != null) {
			banner.setImage(imageService.setImage(bannerVo.getImage()));
		}

		return banner;
	}

	@Override
	@Transactional
	public BannerVo setBannerVo(Banner banner) throws Exception {
		bannerVo = new BannerVo();
		imageVo = setImageVo(banner);
		bannerVo.setImage(imageVo);
		merchantVo = setMerchantVo(banner);
		bannerVo.setMerchant(merchantVo);
		storeVo = setStoreVo(banner);
		bannerVo.setStore(storeVo);
		bannerVo.setIsactive(banner.getIsactive());
		bannerVo.setIsShopsbackerBanner(banner.getIsShopsbackerBanner());
		bannerVo.setBannerId(banner.getBannerId());
		bannerVo.setTabTitleBold(banner.getTabTitleBold());
		bannerVo.setTabTitleSmall(banner.getTabTitleSmall());
		return bannerVo;
	}

	@Override
	public ImageVo setImageVo(Banner banner) throws Exception {
		imageVo = new ImageVo();
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		String url = properties.getProperty("imageUrl");
		imageVo.setName(banner.getImage().getName());
		imageVo.setType(banner.getImage().getType());
		imageVo.setImageId(banner.getImage().getImageId());
		imageVo.setUrl(url + banner.getImage().getUrl());
		return imageVo;
	}

	@Override
	public MerchantVo setMerchantVo(Banner banner) throws Exception {
		merchantVo = new MerchantVo();
		merchantVo.setMerchantId(banner.getMerchant().getMerchantId());
		merchantVo.setName(banner.getMerchant().getName());
		return merchantVo;
	}

	@Override
	public StoreVo setStoreVo(Banner banner) throws Exception {
		storeVo = new StoreVo();
		storeVo.setStoreId(banner.getStore().getStoreId());
		storeVo.setName(banner.getStore().getName());
		return storeVo;
	}

}
