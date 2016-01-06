/**
 * 
 */
package com.mitosis.shopsbacker.vo.common;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mitosis.shopsbacker.model.Gallery;

/**
 * @author Anbukkani Gajendiran
 *
 */
@XmlRootElement
public class GalleryVo {

	private String galleryId;
	private String fileName;
	private String filePath;
	private String url;
	private String type;
	private String base64String;
	private char isSummary;
	private String strFile;
	private Gallery parentGallery;
	private String parentGalleryId;
	private String parentPath;
	private List<GalleryVo> galleries = new ArrayList<GalleryVo>();

	public String getParentGalleryId() {
		return parentGalleryId;
	}

	public void setParentGalleryId(String parentGalleryId) {
		this.parentGalleryId = parentGalleryId;
	}

	public String getStrFile() {
		return strFile;
	}

	public void setStrFile(String strFile) {
		this.strFile = strFile;
	}

	public String getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(String galleryId) {
		this.galleryId = galleryId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public char getIsSummary() {
		return isSummary;
	}

	public void setIsSummary(char isSummary) {
		this.isSummary = isSummary;
	}

	public Gallery getParentGallery() {
		return parentGallery;
	}

	public void setParentGallery(Gallery parentGallery) {
		this.parentGallery = parentGallery;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public List<GalleryVo> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<GalleryVo> galleries) {
		this.galleries = galleries;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBase64String() {
		return base64String;
	}

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}

}
