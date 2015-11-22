package com.mitosis.shopsbacker.inventory.dao;

import java.util.List;

import com.mitosis.shopsbacker.model.Uom;

/**
 * @author fayaz
 */
public interface UomDao<T> {

	public void addUOM(Uom productUnitOfMeasure);

	public void updateUOM(Uom productUnitOfMeasure);
	
	public void removeUOM(Uom productUnitOfMeasure);

	public List<Uom> getAllUOM();

	public Uom getUOMById(String uomId);
	
	public Uom getUomByName(String name);
	
	public Uom getUom(String uomId,String name);

}
