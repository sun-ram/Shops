package com.mitosis.shopsbacker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "city")
public class City implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cityId;
	private String name;
	private State state;
	private char isactive;
	private List<Area> areas = new ArrayList<Area>();

	public City() {
	}
	
	public City(String cityId, State state, String name, char isactive) {
		this.cityId = cityId;
		this.state = state;
		this.name = name;
		this.isactive = isactive;
		
	}
	
	public City(String cityId, State state, String name, char isactive,List<Area> areas) {
		this.cityId = cityId;
		this.state = state;
		this.name = name;
		this.isactive = isactive;
		this.areas=areas;
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "CITY_ID", unique = true, nullable = false, length = 32)
	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Column(name = "NAME", unique = true, nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATE_ID", nullable = false)
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return this.isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
}
