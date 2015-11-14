package com.mitosis.shopsbacker.inventory.service;

import java.util.List;

import com.mitosis.shopsbacker.model.Uom;
/**
 * @author fayaz
 */
public interface UomService<T> {

	public void addUOM(Uom productUnitOfMeasure);

	public void updateUOM(Uom productUnitOfMeasure);

	public List<Uom> getAllUOM();

	public Uom getUOMById(String uomId);
	
	public void removeUOM(Uom productUnitOfMeasure);

}
