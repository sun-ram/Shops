package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.MerchantDao;
import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.RoleService;
import com.mitosis.shopsbacker.admin.service.UserService;
import com.mitosis.shopsbacker.common.service.ImageService;
import com.mitosis.shopsbacker.model.Image;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Role;
import com.mitosis.shopsbacker.model.User;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.RoleName;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.UserVo;
import com.mitosis.shopsbacker.vo.common.ImageVo;

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

	@Autowired
	RoleService<T> roleService;
	
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
	public Merchant setMerchant(MerchantVo merchantVo, Image img) throws Exception {
		Merchant merchant = null;

		if (merchantVo.getMerchantId() == null) {
			merchant = (Merchant) CommonUtil.setAuditColumnInfo(Merchant.class
					.getName());
			merchant.setIsactive('Y');
		} else {
			merchant = merchantDao.getMerchantById(merchantVo.getMerchantId());
			merchant.setUpdated(new Date());
			// TODO: Need to get user from session and set to updated by.
			merchant.setUpdatedby("1234");

			if (merchantVo.getLogo().getImage() != null
					&& merchantVo.getLogo().getType() != null) {
				img = merchant.getLogo();
			}
			
			
		}
		merchant.setName(merchantVo.getName());
		
		if (merchantVo.getLogo().getImage() != null) {
			Image image = imageService.setImage(merchantVo.getLogo());
			merchant.setLogo(image);;
		}
		
		
		
		UserVo userVo = merchantVo.getUser();
		Role role = roleService.getRole(RoleName.MERCHANTADMIN.toString());
		User user = userService.setUser(userVo, role);
		user.setMerchant(merchant);
		merchant.setUser(user);
		return merchant;
	}

	@Override
	public MerchantVo setMerchantVo(Merchant merchant) throws Exception {
		MerchantVo merchantVo = new MerchantVo();
		merchantVo.setName(merchant.getName());
		merchantVo.setMerchantId(merchant.getMerchantId());
		ImageVo imageVo = imageService.setImageVo(merchant);
		merchantVo.setLogo(imageVo);
		User user = merchant.getUser();
		UserVo userVo = userService.setUserVo(user);
		//userVo.setMerchant(merchantVo);
		merchantVo.setUser(userVo);
		return merchantVo;
	}

}
