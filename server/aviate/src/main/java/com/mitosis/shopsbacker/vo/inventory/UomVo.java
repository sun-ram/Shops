/**
 * 
 */
package com.mitosis.shopsbacker.vo.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Anbukkani Gajendran
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class UomVo {
	
	private String uomId;
	private String name;
	private String description;

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
