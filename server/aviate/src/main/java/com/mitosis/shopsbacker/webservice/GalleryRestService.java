package com.mitosis.shopsbacker.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.service.GalleryService;
import com.mitosis.shopsbacker.model.Gallery;
import com.mitosis.shopsbacker.responsevo.GalleryResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.common.GalleryVo;

/**
 * Contains list of rest web service methods for accessing Gallery
 * 
 * @author Anbukkani Gajendiran
 */
@Path("gallery")
@Controller("galleryRestService")
public class GalleryRestService {
	final static Logger log = Logger.getLogger(GalleryRestService.class);

	@Autowired(required = true)
	GalleryService<T> galleryService;

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String addGallery(GalleryVo galleryVo) {
		String responseStr = "";
		ResponseModel responseModel = new ResponseModel();
		try {

			Gallery gallery = (Gallery) CommonUtil
					.setAuditColumnInfo(Gallery.class.getName(), null);
			gallery.setFileName(galleryVo.getFileName());
			gallery.setFilePath(galleryVo.getFilePath());
			gallery.setType(galleryVo.getType());
			gallery.setIsactive('Y');
			gallery.setIsSummary(galleryVo.getIsSummary());
			String parentId = galleryVo.getParentGalleryId();
			Gallery parentGallery = null;
			if (parentId != null) {
				parentGallery = galleryService.getGalleryById(parentId);
				if (parentGallery != null) {
					gallery.setParentGallery(parentGallery);
					gallery.setParentPath(parentGallery.getFileName());
				}
			}
			if (!(galleryVo.getIsSummary() == 'Y')) {
				imageUpload(galleryVo, parentGallery);
			}
			galleryService.addGallery(gallery);
			responseModel.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			String errorMsg = CommonUtil.getErrorMessage(e);
			log.error(errorMsg);
			responseModel.setErrorString(errorMsg);
			responseModel.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(responseModel);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return responseStr;
	}

	private void imageUpload(GalleryVo galleryVo, Gallery parentGallery)
			throws IOException, Exception {

		String filePath = "";
		String defaultAttachmentPath = "";
		String parentPath = "";
		if (parentGallery != null) {
			parentPath = parentGallery.getFilePath();
		}
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		defaultAttachmentPath = properties.getProperty("galleryPath");
		filePath = parentPath + "/" + galleryVo.getFileName() + "/";
		if (CommonUtil.uploadImage(galleryVo.getStrFile(), galleryVo.getType(),
				defaultAttachmentPath + filePath, filePath)) {
			galleryVo.setFilePath(filePath);
		}
	}

	@Path("/galleries")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getGalleries() {
		String responseStr = "";
		GalleryResponseVo galleryResponseVo = new GalleryResponseVo();
		try {
			List<Gallery> parentGalleries = galleryService.getRootGalleries();
						List<GalleryVo> rootGalleryVoList = new ArrayList<GalleryVo>();
						Map<String, GalleryVo> galleryVoParentMap = new HashMap<String, GalleryVo>();
						GalleryVo galleryVo = new GalleryVo();
						getHierarchicalGalleries(parentGalleries, rootGalleryVoList, galleryVoParentMap);
						galleryResponseVo.setGalleries(rootGalleryVoList);
			galleryResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			String errorMsg = CommonUtil.getErrorMessage(e);
			log.error(errorMsg);
			galleryResponseVo.setErrorString(errorMsg);
			galleryResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		try {
			responseStr = CommonUtil.getObjectMapper(galleryResponseVo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return responseStr;
	}

	private void getHierarchicalGalleries(
						List<Gallery> galleries,			
						List<GalleryVo> rootGalleryVoList,
						Map<String, GalleryVo> galleryVoParentMap) throws Exception {
					for (Gallery gallery : galleries) {
						GalleryVo galleryVo = new GalleryVo();
						galleryVo.setFileName(gallery.getFileName()); 
						//galleryVo.setFilePath(gallery.getFilePath());
						galleryVo.setIsSummary(gallery.getIsSummary());
						galleryVo.setGalleryId(gallery.getGalleryId());
						galleryVo.setType(gallery.getType());
						if((gallery.getIsSummary()=='Y')){
							
						}else{
							Properties properties = new Properties();
							properties.load(getClass().getResourceAsStream(
									"/properties/serverurl.properties"));
							String imageUrl = properties.getProperty("imageUrl");
							String url = gallery.getFilePath()+"?p=gallery";
							imageUrl=imageUrl.concat(url);
							galleryVo.setUrl(imageUrl);
						}
						galleryVoParentMap.put(gallery.getGalleryId(),
								galleryVo);
						Gallery parentGallery = gallery.getParentGallery();
						if (parentGallery == null) {
							rootGalleryVoList.add(galleryVo);
						} else {
							String parentGalleryId = parentGallery.getGalleryId(); 
							GalleryVo parentGalleryVo = galleryVoParentMap
									.get(parentGalleryId);
							List<GalleryVo> listOfChildGalleryVo = parentGalleryVo
									.getGalleries();
							listOfChildGalleryVo.add(galleryVo);
							galleryVo.setParentGalleryId(parentGalleryId);
							
						}
						List<Gallery> parentGalleries = gallery.getGalleries();
						if (parentGalleries.size() > 0) {
							getHierarchicalGalleries(parentGalleries, rootGalleryVoList, galleryVoParentMap);
						}
					}
				}
}
