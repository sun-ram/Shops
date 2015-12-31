/**
 * 
 */
package com.mitosis.shopsbacker.vo.inventory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.Storagebin;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.common.AddressVo;

/**
 * @author Anbukkani Gajendran
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class WarehouseVo {

	private String warehouseId;
	private String name;
	private String description;
	private StoreVo store;
	private AddressVo address;
	private Merchant merchant;
	private List<StoragebinVo> storagebins= new ArrayList<StoragebinVo>();
	private String userId;

	/**
	 * @return the storagebins
	 */
	public List<StoragebinVo> getStoragebins() {
		return storagebins;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param storagebins the storagebins to set
	 */
	public void setStoragebins(List<StoragebinVo> storagebins) {
		this.storagebins = storagebins;
	}

	/**
	 * @return the merchant
	 */
	public Merchant getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant the merchant to set
	 */
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(AddressVo address) {
		this.address = address;
	}

	/**
	 * @return the addressVo
	 */
	public AddressVo getAddress() {
		return address;
	}

	/**
	 * @return the store
	 */
	public StoreVo getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(StoreVo store) {
		this.store = store;
	}

	/**
	 * @return the warehouseId
	 */
	public String getWarehouseId() {
		return warehouseId;
	}

	/**
	 * @param warehouseId
	 *            the warehouseId to set
	 */
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
