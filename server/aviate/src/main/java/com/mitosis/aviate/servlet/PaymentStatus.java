package com.mitosis.aviate.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

import com.mitosis.aviate.webservice.ecommerce.CheckOutServices;

/**
 * Servlet implementation class PaymentStatus
 */
@WebServlet("/product")
public class PaymentStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Properties properties = new Properties();
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		properties.load(getClass().getResourceAsStream(
				"/properties/serverurl.properties"));
		System.out.println("it works");
		System.out.println("Payment status"+request.getParameter("exact_ctr"));
		String paymentMethod = request.getParameter("x_method");
		/*PrintWriter writter = response.getWriter();
		writter.println("<p style='color:red'>"+request.getParameter("exact_ctr")+"</p>");*/
		
		String result = request.getParameter("exact_ctr");
		String[] transaction = result.split("REFERENCE #     :");
		String transactionNo = transaction[1].substring(1,10);
		String[] order = result.split("TRANS. REF.     : ");
		String orderId = order[1].substring(0, 10);
		
		CheckOutServices checkOutServices = new CheckOutServices();
		checkOutServices.conformPayment(orderId.trim(), transactionNo.trim(), paymentMethod);
		// Set response content type
	      response.setContentType("text/html");

	      // New location to be redirected
	      //String site = new String("http://localhost:8080/aviate/#/paymentType");
	      String site = new String(properties.getProperty("redirectUrl"));
	      response.setStatus(response.SC_MOVED_TEMPORARILY);
	      response.setHeader("Location", site);    
	}

}