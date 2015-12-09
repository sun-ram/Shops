package com.mitosis.shopsbacker.customer.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.customer.dao.CustomerDao;
import com.mitosis.shopsbacker.customer.dao.FavouriteDao;
import com.mitosis.shopsbacker.customer.service.FavouriteService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Favourite;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.vo.customer.FavouriteVo;

/**
 * @author prabakaran
 *
 * @param <T>
 */
@Service("favouriteServiceImpl")
public class FavouriteServiceImpl<T> extends CustomHibernateDaoSupport<T>
		implements FavouriteService<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	FavouriteDao<T> favouriteDao;
	
	@Autowired
	 CustomerDao<T> customerDao;

	public FavouriteDao<T> getFavourite() {
		return favouriteDao;
	}

	public void setFavourite(FavouriteDao<T> favourite) {
		this.favouriteDao = favourite;
	}

	@Override
	public void updateFavourite(Favourite favourite) {
		getFavourite().updateFavourite(favourite);
	}

	@Override
	public void deleteFavourite(Favourite favourite) {
		getFavourite().deleteFavourite(favourite);
	}

	@Override
	public List<Favourite> getFavourites(Customer customer, Store store) {
		return getFavourite().getFavourites(customer, store);
	}

	@Override
	public List<Favourite> getFavourites(Customer customer, Product product,
			Store store) {
		return getFavourite().getFavourites(customer, product, store);
	}

	@Override
	public void saveFavourite(Favourite favourite) {
		getFavourite().saveFavourite(favourite);
	}

	public Favourite getFavourites(String favouriteId) {
		return favouriteDao.getFavourite(favouriteId);
	}
	
	@Override
	public Favourite getFavouriteByName(String favouriteName, String customerId) {
		Customer customer = customerDao.getCustomerInfoById(customerId);
		return getFavourite().getFavouriteByName(favouriteName, customer);
	}

	@Override
	public FavouriteVo setFavouriteVo(Favourite favourite) {
		FavouriteVo favouriteVo = new FavouriteVo();
		favouriteVo.setFavouriteId(favourite.getFavouriteId());
		favouriteVo.setName(favourite.getName());
		favouriteVo.setSalesOrderId(favourite.getSalesOrder().getSalesOrderId());
		return favouriteVo;
	}

}
