package com.mitosis.shopsbacker.customer.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.daoimpl.CustomHibernateDaoSupport;
import com.mitosis.shopsbacker.customer.dao.FavouriteDao;
import com.mitosis.shopsbacker.customer.service.FavouriteService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Favourite;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;

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
	FavouriteDao<T> favourite;

	public FavouriteDao<T> getFavourite() {
		return favourite;
	}

	public void setFavourite(FavouriteDao<T> favourite) {
		this.favourite = favourite;
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
	@Transactional
	public void saveFavourite(Favourite favourite) {
		getFavourite().saveFavourite(favourite);
	}

	@Override
	public Favourite getFavouriteBtName(String favouriteName) {
		return getFavourite().getFavouriteBtName(favouriteName);
	}

}
