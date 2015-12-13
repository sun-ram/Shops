/**
 * 
 */
package com.mitosis.shopsbacker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Anbukkani Gajendiran
 *
 */

@Entity
@Table(name = "area")
public class Area {

	private static final long serialVersionUID = 1L;
	private String areaId;
	private String name;
	private City city;
	private char isactive;

	public Area() {

	}

	public Area(String areaId, City city, String name, char isactive) {
		this.areaId = areaId;
		this.city = city;
		this.name = name;
		this.isactive = isactive;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "AREA_ID", unique = true, nullable = false, length = 32)
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID", nullable = false)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

}
