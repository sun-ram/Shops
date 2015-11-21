package com.mitosis.shopsbacker.responsevo;

import java.util.ArrayList;
import java.util.List;

import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.BannerVo;

public class BannerResponseVo extends ResponseModel {
			
		public List<BannerVo> bannerList = new ArrayList<BannerVo>();

		public List<BannerVo> getBannerList() {
			return bannerList;
		}

		public void setBannerList(List<BannerVo> bannerList) {
			this.bannerList = bannerList;
		}

}
