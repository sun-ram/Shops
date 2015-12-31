/**
 * 
 */
package com.mitosis.shopsbacker.vo.inventory;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.vo.admin.StoreVo;

/**
 * @author Anbukkani Gajendran
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class StoragebinVo {

	private String storagebinId;
	private Merchant merchant;
	private WarehouseVo warehouse;
	private StoreVo store;
	private String name;
	private String description;
	private String row;
	private String stack;
	private String level;
	private Date created;
	private String userId;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the storagebinId
	 */
	public String getStoragebinId() {
		return storagebinId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @param storagebinId
	 *            the storagebinId to set
	 */
	public void setStoragebinId(String storagebinId) {
		this.storagebinId = storagebinId;
	}

	/**
	 * @return the merchant
	 */
	public Merchant getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant
	 *            the merchant to set
	 */
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	/**
	 * @return the warehouse
	 */
	public WarehouseVo getWarehouse() {
		return warehouse;
	}

	/**
	 * @param warehouse
	 *            the warehouse to set
	 */
	public void setWarehouse(WarehouseVo warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * @return the store
	 */
	public StoreVo getStore() {
		return store;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(StoreVo store) {
		this.store = store;
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

	/**
	 * @return the row
	 */
	public String getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(String row) {
		this.row = row;
	}

	/**
	 * @return the stack
	 */
	public String getStack() {
		return stack;
	}

	/**
	 * @param stack
	 *            the stack to set
	 */
	public void setStack(String stack) {
		this.stack = stack;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
}
