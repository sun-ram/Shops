package com.mitosis.aviate.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.mitosis.aviate.dao.ProductUpdateDao;
import com.mitosis.aviate.dao.StoreDao;
import com.mitosis.aviate.dao.daoimpl.ProductUpdateDaoImpl;
import com.mitosis.aviate.dao.daoimpl.StoreDaoImpl;
import com.mitosis.aviate.model.ProductImages;
import com.mitosis.aviate.model.StoreModel;

/**
 * Servlet implementation class ImageController
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductUpdateDao productUpdateDao = new ProductUpdateDaoImpl();
	StoreDao storeDao = new StoreDaoImpl();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imageName = request.getParameter("imageName");
		String storeLogoName = request.getParameter("storeLogoName");

		if(imageName != null && imageName != ""){
			ProductImages productImage = productUpdateDao.getProductImageByImageName(imageName);
			if(productImage!=null && productImage.getImage()!=null){
				InputStream in = new ByteArrayInputStream(productImage.getImage());
				ServletOutputStream out = response.getOutputStream();
				IOUtils.copy(in, response.getOutputStream());
				String imageType =productImage.getImageType();
				String contentType= "image/"+imageType;
				response.setContentType(contentType);
				//resp.setContentType("image/png");
				//response.setHeader("Content-Disposition", "inline; filename="+fname);
				//resp.setHeader("Content-Disposition", "attachment; filename="+fname);
				in.close();
				out.close();
			}else{
				InputStream image = this.getClass().getClassLoader().getResourceAsStream("images/noimage.jpg");
				ServletOutputStream out = response.getOutputStream();
				IOUtils.copy(image, response.getOutputStream());
				String imageType = "jpg";
				String contentType= "image/"+imageType;
				response.setContentType(contentType);
				image.close();
				out.close();
			}
		}else if(storeLogoName != null && storeLogoName != ""){
			
			StoreModel storeModel = storeDao.getStoreLogoByImageName(storeLogoName);
			if(storeModel!=null && storeModel.getImage()!=null){
				InputStream in = new ByteArrayInputStream(storeModel.getImage());
				ServletOutputStream out = response.getOutputStream();
				IOUtils.copy(in, response.getOutputStream());
				String imageType =storeModel.getImageType();
				String contentType= "image/"+imageType;
				response.setContentType(contentType);
				//resp.setContentType("image/png");
				//response.setHeader("Content-Disposition", "inline; filename="+fname);
				//resp.setHeader("Content-Disposition", "attachment; filename="+fname);
				in.close();
				out.close();
			}else{
				InputStream image = this.getClass().getClassLoader().getResourceAsStream("images/noimage.jpg");
				ServletOutputStream out = response.getOutputStream();
				IOUtils.copy(image, response.getOutputStream());
				String imageType = "jpg";
				String contentType= "image/"+imageType;
				response.setContentType(contentType);
				image.close();
				out.close();
			}
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Image servlet post method");	}

}
