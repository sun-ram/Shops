package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.mitosis.shopsbacker.admin.dao.TaxDao;
import com.mitosis.shopsbacker.admin.service.StoragebinService;
import com.mitosis.shopsbacker.admin.service.TaxService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Tax;
/**
 * @author RiyazKhan.M
 */
public class TaxServiceImpl<T> implements TaxService<T>,
Serializable {


	private static final long serialVersionUID = 1L;
	
	@Autowired
	TaxDao taxDao;

	@Override
	public void addTax(Tax tax) {
		taxDao.addTax(tax);
		
	}

	@Override
	public void removeTax(Tax tax) {
		taxDao.removeTax(tax);
		
	}

	@Override
	public void updateTax(Tax tax) {
		taxDao.updateTax(tax);
		
	}

	@Override
	public Tax getTax(Merchant merchant) {
		return taxDao.getTax(merchant);
	}
}
