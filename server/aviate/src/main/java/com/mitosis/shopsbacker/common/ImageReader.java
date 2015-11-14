package com.mitosis.shopsbacker.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;


/**
 * ImageReader is to read image file from server directory
 * 
 * @author prabakaran
 * 
 */
public class ImageReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ImageReader.class);
	private static String IMAGE_FILE_LOCATION;
	private static String IMAGE_FILE_URL;
	@Override
	public void init() throws ServletException {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			IMAGE_FILE_LOCATION = properties.getProperty("imagePath");
			IMAGE_FILE_URL = properties.getProperty("imageUrl");
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.info(req.getRequestURL());
		try{
				String fname = req.getRequestURL().substring(IMAGE_FILE_URL.length()-1, req.getRequestURL().length()-1);
				File file = new File(IMAGE_FILE_LOCATION+fname);
				InputStream in = new FileInputStream(file);
				ServletOutputStream out = resp.getOutputStream();
				IOUtils.copy(in, resp.getOutputStream());
				String imageType =fname.substring(fname.lastIndexOf(".")+1, fname.length());
				String contentType= "image/"+imageType;
				resp.setContentType(contentType);
				resp.setHeader("Content-Disposition", "inline; filename="+fname);
				in.close();
				out.close();
		}catch(Exception e){
			logger.error(e);
		}
	}
}
