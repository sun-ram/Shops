package com.mitosis.shopsbacker.common.serviceimpl;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.mitosis.shopsbacker.order.service.SalesOrderService;

/**
 * Servlet implementation class PaymentRedirect
 */
@WebServlet("/transaction")
@Controller("paymentRedirect")
public class PaymentRedirect<T> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Properties properties = new Properties();

	@Autowired
	SalesOrderService<T> salesOrderService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentRedirect() {
		super();
	}
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                System.out.println( paramName +"   :"+ paramValue);
            }
        }*/

		String salesOrderNo = "";
		String transactionNo = "";
		String paymentMethod = "";
		String requestId = "";

		if("0".equalsIgnoreCase(request.getParameter("ResponseCode")) && "Transaction Successful".equalsIgnoreCase(request.getParameter("ResponseMessage"))){
			salesOrderNo = request.getParameter("Description").trim();
			transactionNo = request.getParameter("PaymentID").trim();
			paymentMethod = request.getParameter("PaymentMethod").trim();
			requestId = request.getParameter("RequestID").trim();
					
					
			/*System.out.println("ResponseMessage   :"+ request.getParameter("ResponseMessage"));
			System.out.println("PaymentID         :"+ request.getParameter("PaymentID"));
			System.out.println("Description       :"+ request.getParameter("Description"));
			System.out.println("TransactionID     :"+ request.getParameter("TransactionID"));
			System.out.println("PaymentMethod     :"+ request.getParameter("PaymentMethod"));
			System.out.println("RequestID         :"+ request.getParameter("RequestID"));*/
			
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			boolean flag = salesOrderService.paymentConfimation(salesOrderNo, transactionNo, paymentMethod, requestId);
		}
		
		// Set response content type
		response.setContentType("text/html");

		// New location to be redirected
		String site = new String(properties.getProperty("redirectUrl"));
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site.concat(request.getParameter("Description").trim()));    
	}

}
