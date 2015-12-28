/**
 * 
 */
package com.mitosis.shopsbacker.vo.inventory;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Anbukkani Gajendran
 *
 */
@XmlRootElement
public class UomVo {
	
	private String uomId;
	private String name;
	private String description;
	private String userId;

	/**
	 * @return the uomId
	 */
	public String getUomId() {
		return uomId;
	}
	/**
	 * @param uomId the uomId to set
	 */
	public void setUomId(String uomId) {
		this.uomId = uomId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
