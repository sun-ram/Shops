package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Banner;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.admin.BannerVo;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;

public interface BannerService<T> {
	
	public void saveBanner(Banner banner);
	public void updateBanner(Banner banner);
	public List<Banner> getBannerListByStore(Store store);
	public List<Banner> getBannerListByFlag(char isShopsbackerBanner);
	public void deleteBanner(String id) throws Exception;
	public Banner getBannerById(String id);
	public Banner setBanner(BannerVo bannerVo,Banner bannerval) throws Exception;
	public BannerVo setBannerVo (Banner bannerList) throws Exception;
	public ImageVo setImageVo(Banner bannerList) throws Exception;
	public MerchantVo setMerchantVo(Banner bannerList) throws Exception;
	public StoreVo setStoreVo(Banner bannerList) throws Exception;
	

}
