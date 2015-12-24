/**
 * 
 */
package com.mitosis.shopsbacker.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Entity
@Table(name = "gallery")
public class Gallery implements Serializable {

	private static final long serialVersionUID = 1L;
	private String galleryId;
	private String fileName;
	private String filePath;
	private String type;
	private char isSummary;
	private Gallery parentGallery;
	private String parentPath;
	private char isactive;
	private String createdby;
	private String updatedby;
	private Date created;
	private Date updated;
	private List<Gallery> galleries = new ArrayList<Gallery>();

	public Gallery() {

	}

	public Gallery(String galleryId, String fileName, String filePath,
			String type, char isSummary, Gallery parentGallery,
			String parentPath, char isactive, String createdby,
			String updatedby, Date created, Date updated,
			List<Gallery> galleries) {
		this.galleryId = galleryId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.type = type;
		this.isSummary = isSummary;
		this.parentGallery = parentGallery;
		this.parentPath = parentPath;
		this.isactive = isactive;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.created = created;
		this.updated = updated;
		this.galleries = galleries;
	}

	public Gallery(String galleryId, String fileName, String filePath,
			String type, char isSummary, Gallery parentGallery,
			String parentPath, char isactive, String createdby,
			String updatedby, Date created, Date updated) {
		this.galleryId = galleryId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.type = type;
		this.isSummary = isSummary;
		this.parentGallery = parentGallery;
		this.parentPath = parentPath;
		this.isactive = isactive;
		this.createdby = createdby;
		this.updatedby = updatedby;
		this.created = created;
		this.updated = updated;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "GALLERY_ID", unique = true, nullable = false, length = 32)
	public String getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(String galleryId) {
		this.galleryId = galleryId;
	}

	@Column(name = "FILE_NAME", nullable = false, length = 100)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_PATH", nullable = false, length = 1000)
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "TYPE", nullable = false, length = 10)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "IS_SUMMARY", nullable = false, length = 1)
	public char getIsSummary() {
		return isSummary;
	}

	public void setIsSummary(char isSummary) {
		this.isSummary = isSummary;
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_GALLAERY_ID")
	public Gallery getParentGallery() {
		return parentGallery;
	}

	public void setParentGallery(Gallery parentGallery) {
		this.parentGallery = parentGallery;
	}

	@Column(name = "PARENT_PATH", nullable = false, length = 1000)
	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	@Column(name = "ISACTIVE", nullable = false, length = 1)
	public char getIsactive() {
		return isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	@Column(name = "CREATEDBY", length = 32)
	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	@Column(name = "UPDATEDBY", length = 32)
	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, length = 19)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED", nullable = false, length = 19)
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "parentGallery")
	public List<Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<Gallery> galleries) {
		this.galleries = galleries;
	}

}
