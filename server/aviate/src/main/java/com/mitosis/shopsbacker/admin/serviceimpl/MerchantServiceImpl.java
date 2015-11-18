package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.MerchantDao;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;

@Service("merchantServiceImpl")
public class MerchantServiceImpl<T> implements MerchantService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	MerchantDao<T> merchantDao;
	
	@Autowired
	ImageService<T> imageService;
	
	@Autowired
	UserService<T> userService;

	public ImageService<T> getImageService() {
		return imageService;
	}

	public void setImageService(ImageService<T> imageService) {
		this.imageService = imageService;
	}

	public UserService<T> getUserService() {
		return userService;
	}

	public void setUserService(UserService<T> userService) {
		this.userService = userService;
	}

	public MerchantDao<T> getMerchantDao() {
		return merchantDao;
	}

	public void setMerchantDao(MerchantDao<T> merchantDao) {
		this.merchantDao = merchantDao;
	}

	@Override
	@Transactional
	public void saveMerchant(Merchant merchant) {
		getMerchantDao().saveMerchant(merchant);
	}

	@Override
	@Transactional
	public void updateMerchant(Merchant merchant) {
		getMerchantDao().updateMerchant(merchant);
	}

	@Override
	@Transactional
	public List<Merchant> getMerchantList() {
		return getMerchantDao().getMerchantList();
	}

	@Override
	@Transactional
	public void deleteMerchant(String id) {
		getMerchantDao().deleteMerchant(getMerchantById(id));
	}

	@Override
	@Transactional
	public List<Merchant> getMerchantListByName(String name) {
		return getMerchantDao().getMerchantListByName(name);
	}

	@Override
	@Transactional
	public Merchant getMerchantById(String id) {
		return getMerchantDao().getMerchantById(id);
	}

	@Override
	@Transactional
	public Merchant setMerchant(MerchantVo merchantVo) throws Exception {
		Merchant merchant = (Merchant) CommonUtil
				.setAuditColumnInfo(Merchant.class.getName());
		merchant.setName(merchantVo.getName());

		Image image = imageService.setImage(merchantVo);
		merchant.setLogo(image);

		UserVo userVo = merchantVo.getUser();
		Role role = new Role();
		role.setName("MERCHANTADMIN");
		User user = userService.setUser(userVo,role);
		user.setMerchant(merchant);
		//merchant.setUser(user);
		//user.setMerchant(merchant);
		return merchant;
	}

	@Override
	public MerchantVo setMerchantVo(Merchant merchant) {
		// TODO Auto-generated method stub
		return null;
	}

}
