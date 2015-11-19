package com.mitosis.shopsbacker.admin.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.dao.TaxDao;
import com.mitosis.shopsbacker.admin.service.TaxService;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Tax;
/**
 * @author RiyazKhan.M
 */
@Service("taxServiceImpl")
public class TaxServiceImpl<T> implements TaxService<T>,
Serializable {


	private static final long serialVersionUID = 1L;
	
	@Autowired
	TaxDao<T> taxDao;
	
	public TaxDao<T> getTaxDao() {
		return taxDao;
	}

	public void setTaxDao(TaxDao<T> taxDao) {
		this.taxDao = taxDao;
	}

	@Override
	@Transactional
	public void addTax(Tax tax) {
		taxDao.addTax(tax);
		
	}

	@Override
	@Transactional
	public void removeTax(Tax tax) {
		taxDao.removeTax(tax);
		
	}

	@Override
	@Transactional
	public void updateTax(Tax tax) {
		taxDao.updateTax(tax);
		
	}

	@Override
	@Transactional
	public Tax getTaxById(String id) {
		return taxDao.getTaxById(id);
	}

	@Override
	@Transactional
	public List<Tax> getTax(Merchant merchant) {
		return taxDao.getTax(merchant);
	}

	@Override
	@Transactional
	public List<Tax> getTaxListByName(String param) {
		return taxDao.getTaxListByName(param);
	}
}
