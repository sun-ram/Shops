package com.mitosis.shopsbacker.customer.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Favourite;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;

/**
 * @author prabakaran
 *
 * @param <T>
 */
public interface FavouriteDao<T> {

	public void updateFavourite(Favourite favourite);
	
	public void saveFavourite(Favourite favourite);

	public void deleteFavourite(Favourite favourite);

	public List<Favourite> getFavourites(Customer customer, Store store);
	
	public List<Favourite> getFavourites(Customer customer,Product product, Store store);
	
	public Favourite getFavouriteByName(String favouriteName, Customer customerId);

}
