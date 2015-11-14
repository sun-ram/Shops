package com.mitosis.shopsbacker.admin.service;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Tax;
/**
 * @author RiyazKhan.M
 */
public interface TaxService<T> {
	
	public void addTax(Tax tax);
	
	public void removeTax(Tax tax);
	
	public void updateTax(Tax tax);
	
	public Tax getTax(Merchant merchant);

}
