package com.mitosis.shopsbacker.inventory.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.inventory.dao.UomDao;
import com.mitosis.shopsbacker.inventory.service.UomService;
import com.mitosis.shopsbacker.model.Uom;
import com.mitosis.shopsbacker.vo.inventory.UomVo;

/**
 * @author fayaz
 */
@Service("uomServiceImpl")
public class UomServiceImpl<T> implements UomService<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	UomDao<T> uomDao;

	public UomDao<T> getUomDao() {
		return uomDao;
	}

	public void setUomDao(UomDao<T> uomDao) {
		this.uomDao = uomDao;
	}

	@Override
	@Transactional
	public void addUOM(Uom productUnitOfMeasure) {
		uomDao.addUOM(productUnitOfMeasure);

	}

	@Override
	@Transactional
	public void updateUOM(Uom productUnitOfMeasure) {
		uomDao.updateUOM(productUnitOfMeasure);
	}

	@Override
	@Transactional
	public List<Uom> getAllUOM() {
		return uomDao.getAllUOM();
	}

	@Override
	@Transactional
	public Uom getUOMById(String uomId) {
		return uomDao.getUOMById(uomId);
	}

	@Override
	@Transactional
	public void removeUOM(Uom productUnitOfMeasure) {
		uomDao.removeUOM(productUnitOfMeasure);

	}

	@Override
	@Transactional
	public Uom getUomByName(String name) {
		return uomDao.getUomByName(name);
	}

	@SuppressWarnings("null")
	@Override
	public UomVo setUomVo(Uom uom) {
		UomVo uomVo = new UomVo();

		uomVo.setName(uom.getName());
		uomVo.setDescription(uom.getDescription());
		return uomVo;
	}

	public Uom getUom(String uomId, String name) {
		return uomDao.getUom(uomId, name);
	}

}
