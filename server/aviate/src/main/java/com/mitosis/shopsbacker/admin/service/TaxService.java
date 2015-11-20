package com.mitosis.shopsbacker.admin.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Tax;
import com.mitosis.shopsbacker.vo.admin.TaxVo;
/**
 * @author RiyazKhan.M
 */
public interface TaxService<T> {
	
	public void addTax(Tax tax);
	
	public void removeTax(Tax tax);
	
	public void updateTax(Tax tax);
	
	public List<Tax> getTax(Merchant merchant);
	
	public Tax getTaxById(String id);

	public List<Tax> getTaxListByName(String param);
	
	public Tax setTax(TaxVo taxVo) throws Exception ;
	
	public TaxVo setTaxVo (Tax tax);

}
