package com.mitosis.shopsbacker.vo.common;

// Generated Nov 12, 2015 6:16:19 PM 

import javax.xml.bind.annotation.XmlRootElement;

/**
 *  Created by Prabakaran.15/11/2015
 *  Reviewed by Sundaram  27/11/2015
 */
@XmlRootElement
public class ImageVo {

	private String imageId;
	private String name;
	private String type;
	private String imageFrom;
	private String url;
	private String image;
	
	public String getImageFrom() {
		return imageFrom;
	}
	public void setImageFrom(String imageFrom) {
		this.imageFrom = imageFrom;
	}
	public String getImageId() {
		return imageId;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getUrl() {
		return url;
	}
	public String getImage() {
		return image;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
