package com.mitosis.shopsbacker.common.serviceimpl;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.order.serviceimpl.SalesOrderServiceImpl;

/**
 * Servlet implementation class PaymentRedirect
 */
@WebServlet("/transaction")
public class PaymentRedirect<T> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Properties properties = new Properties();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentRedirect() {
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
		String orderNo = order[1].substring(0, 20);
		SalesOrderService<T> salesOrderService = new SalesOrderServiceImpl<T>();
		SalesOrder salesOrder= salesOrderService.paymentConfimation(orderNo.trim(), transactionNo.trim(), paymentMethod);
		// Set response content type
	      response.setContentType("text/html");

	      // New location to be redirected
	      //String site = new String("http://localhost:8080/aviate/#/paymentType");
	      String site = new String(properties.getProperty("redirectUrl"));
	      response.setStatus(response.SC_MOVED_TEMPORARILY);
	      response.setHeader("Location", site.concat(salesOrder.getSalesOrderId()));    
	}

}
