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

import com.mitosis.shopsbacker.order.service.BillingService;

@WebServlet("/transactionOfBilling")
@Controller("paymentOfBillingRedirect")
public class paymentOfBillingRedirect<T> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Properties properties = new Properties();

	@Autowired
	BillingService<T> billingService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public paymentOfBillingRedirect() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * Enumeration<String> parameterNames = request.getParameterNames();
		 * while (parameterNames.hasMoreElements()) { String paramName =
		 * parameterNames.nextElement(); String[] paramValues =
		 * request.getParameterValues(paramName); for (int i = 0; i <
		 * paramValues.length; i++) { String paramValue = paramValues[i];
		 * System.out.println( paramName +"   :"+ paramValue); } }
		 */

		String billingNo = "";
		String transactionNo = "";
		String paymentMethod = "";
		String requestId = "";

		if ("0".equalsIgnoreCase(request.getParameter("ResponseCode"))
				&& "Transaction Successful".equalsIgnoreCase(request
						.getParameter("ResponseMessage"))) {
			billingNo = request.getParameter("Description").trim();
			transactionNo = request.getParameter("TransactionID").trim();
			paymentMethod = request.getParameter("PaymentMethod").trim();
			requestId = request.getParameter("RequestID").trim();
			String paymentId = request.getParameter("PaymentID").trim();
			String responseCode = request.getParameter("ResponseCode");
			String responseMessage =request
					.getParameter("ResponseMessage");
			String referenceNo = request.getParameter("MerchantRefNo").trim();;

			/*
			 * System.out.println("ResponseMessage   :"+
			 * request.getParameter("ResponseMessage"));
			 * System.out.println("PaymentID         :"+
			 * request.getParameter("PaymentID"));
			 * System.out.println("Description       :"+
			 * request.getParameter("Description"));
			 * System.out.println("TransactionID     :"+
			 * request.getParameter("TransactionID"));
			 * System.out.println("PaymentMethod     :"+
			 * request.getParameter("PaymentMethod"));
			 * System.out.println("RequestID         :"+
			 * request.getParameter("RequestID"));
			 */

			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
			boolean flag = billingService.updateBilling(billingNo,
					paymentId, paymentMethod, requestId, transactionNo,
					responseCode, responseMessage, referenceNo);

			// paymentConfimation(salesOrderNo, transactionNo, paymentMethod,
			// requestId);
		}

		// Set response content type
		response.setContentType("text/html");

		// New location to be redirected
		String site = new String(properties.getProperty("redirectUrlForBilling"));
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location",
				site.concat("merchantadmin"));
	}
}
