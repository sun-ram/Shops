package com.mitosis.shopsbacker.responsevo;

import java.util.List;

import com.mitosis.shopsbacker.model.Favourite;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.customer.CustomerVo;
import com.mitosis.shopsbacker.vo.customer.FavouriteVo;

public class FavouriteResponseVo extends ResponseModel{

	public List<FavouriteVo> favourites;
	private CustomerVo customerVo;
	private StoreVo storeVo;
	
	public CustomerVo getCustomerVo() {
		return customerVo;
	}

	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}

	public StoreVo getStoreVo() {
		return storeVo;
	}

	public void setStoreVo(StoreVo storeVo) {
		this.storeVo = storeVo;
	}

	public List<FavouriteVo> getFavourites() {
		return favourites;
	}

	public void setFavourites(List<FavouriteVo> favourites) {
		this.favourites = favourites;
	}
	
	
	
	
}
