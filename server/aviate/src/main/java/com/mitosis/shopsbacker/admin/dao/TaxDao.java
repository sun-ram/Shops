package com.mitosis.shopsbacker.admin.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Tax;
/**
 * @author RiyazKhan.M
 */
public interface TaxDao<T> {
	
	public void addTax(Tax tax);
	
	public void removeTax(Tax tax);
	
	public void updateTax(Tax tax);
	
	public List<Tax> getTax(Merchant merchant);
	
	public Tax getTaxById(String id);

}
