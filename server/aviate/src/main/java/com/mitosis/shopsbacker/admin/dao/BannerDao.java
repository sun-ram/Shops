package com.mitosis.shopsbacker.admin.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Banner;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;


public interface BannerDao<T> {
	
	public void saveBanner(Banner banner);
	public void updateBanner(Banner banner);
	public List<Banner> getBannerList(Store store);
	public void deleteBanner(Banner banner);
	public Banner getBannerById(String id);
}
