package com.mitosis.shopsbacker.webservice;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.BannerService;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Banner;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.BannerResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.BannerVo;

/**
 * @author kathir reviewed by sundaram 24-11-2015
 *
 */
@Path("banner")
@Controller("bannerRestServices")
public class BannerRestServices {
	final static Logger log = Logger.getLogger(BannerRestServices.class);

	@Autowired
	BannerService<T> bannerService;

	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	ImageService<T> imageService;

	Merchant merchant = null;

	Store store = null;

	Banner banner, bannerVal = null;

	ResponseModel response = new ResponseModel();

	@Path("/addbanner")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addBanner(BannerVo bannerVo) {
		try {
			bannerVal = new Banner();
			bannerImageUpload(bannerVo);
			banner = bannerService.setBanner(bannerVo, bannerVal);
			bannerService.saveBanner(banner);
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	public void bannerImageUpload(BannerVo bannerVo) throws IOException,
			Exception {

		if (bannerVo.getImage().getImage() == null) {
			return;
		}

		String bannerImagePath = "";
		String defaultImagePath = "";
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		defaultImagePath = properties.getProperty("imagePath");
		if (bannerVo.getStore() != null) {
			if (bannerVo.getStore().getStoreId() != null) {
				store = storeService.getStoreById(bannerVo.getStore()
						.getStoreId());
				bannerImagePath = "Banner/Store/" + store.getName() + "/";
			} else {
				bannerImagePath = "Banner/SuperAdmin/";
			}
		} else {
			bannerImagePath = "Banner/SuperAdmin/";
		}
		String imageName = UUID.randomUUID().toString().replace("-", "");
		if (CommonUtil.uploadImage(bannerVo.getImage().getImage(), bannerVo
				.getImage().getType(), defaultImagePath + bannerImagePath,
				imageName)) {
			bannerVo.getImage().setName(imageName);
			bannerVo.getImage().setUrl(
					bannerImagePath + imageName + "."
							+ bannerVo.getImage().getType());
		}
	}

	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel updateBanner(BannerVo bannerVo) {
		try {
			bannerVal = new Banner();
			bannerImageUpload(bannerVo);

			banner = bannerService.setBanner(bannerVo, bannerVal);

			bannerService.updateBanner(banner);

			if (banner.getBannerId() != null && bannerVal.getImage() != null) {
				imageService.deleteImage(bannerVal.getImage());
			}
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

	@Path("/getbannerlist")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BannerResponseVo getBannerList(BannerVo banners) {

		BannerResponseVo bannerResponse = new BannerResponseVo();
		try {
			List<Banner> bannerList = null;
			if (banners.getStore() != null) {
				store = storeService.getStoreById(banners.getStore()
						.getStoreId());
				bannerList = bannerService.getBannerListByStore(store);
			} else {
				bannerList = bannerService.getBannerListByFlag(banners
						.getIsShopsbackerBanner());
			}
			for (Banner banner : bannerList) {
				BannerVo bannerVo = bannerService.setBannerVo(banner);
				bannerResponse.getBannerList().add(bannerVo);
			}
			bannerResponse.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			bannerResponse.setStatus(SBMessageStatus.FAILURE.getValue());
			bannerResponse.setErrorString(CommonUtil.getErrorMessage(e));
		}
		return bannerResponse;
	}

	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel deleteBanner(BannerVo bannerVo) {
		try {
			bannerService.deleteBanner(bannerVo.getBannerId());
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			response = CommonUtil.addStatusMessage(e, response);
		}
		return response;
	}

}
