package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderReturn;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.model.Transaction;
import com.mitosis.shopsbacker.order.dao.SalesOrderReturnDao;
import com.mitosis.shopsbacker.order.dao.TransactionDao;
import com.mitosis.shopsbacker.order.service.SalesOrderReturnService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.vo.order.SalesOrderReturnVo;

/**
 * @author Kartheeswaran
 *
 * @param <T>
 */
@Service("salesOrderReturnServiceImpl")
public class SalesOrderReturnServiceImpl<T> implements
						SalesOrderReturnService<T>, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8232879086937675374L;
	
	@Autowired
	SalesOrderReturnDao<T> salesOrderReturnDao;
	
	@Autowired
	SalesOrderService<T> salesOrderService;
	
	@Autowired
	TransactionDao<T> transactionDao;

	@Override
	public String createSalesOrderReturn(SalesOrderReturn orderReturn) {
		String orderReturnId = salesOrderReturnDao.createSalesOrderReturn(orderReturn);
		return orderReturnId;
	}

	@Override
	public boolean updateSalesOrderReturn(
			SalesOrderReturn orderReturn) {
		return salesOrderReturnDao.updateSalesOrderReturn(orderReturn);
	}

	@Override
	public boolean deleteSalesOrderReturn(
			SalesOrderReturn orderReturn) {
		return salesOrderReturnDao.deleteSalesOrderReturn(orderReturn);
	}

	@Override
	public SalesOrderReturn getSalesOrderReturnById(String id) {
		return salesOrderReturnDao.getSalesOrderReturnById(id);
	}

	@Override
	public List<SalesOrderReturn> getSalesOrderByStore(Store store) {
		return salesOrderReturnDao.getSalesOrderByStore(store);
	}

	@Override
	public SalesOrderReturnVo setSalesOrderToVo(
			SalesOrderReturn salesOrderReturn) {
		SalesOrderReturnVo salesOrderReturnVo=new SalesOrderReturnVo();
		try {
			salesOrderReturnVo.setSalesOrderReturnId(salesOrderReturn.getSalesOrderReturnId());
			salesOrderReturnVo.setSalesOrder(salesOrderService.setSalesOrderVo(salesOrderReturn.getSalesOrder(), false));
			salesOrderReturnVo.setReturnReason(salesOrderReturn.getReturnReason());
			salesOrderReturnVo.setReturnTotalAmount(salesOrderReturn.getReturnTotalAmount());
			salesOrderReturnVo.setReturnTaxAmount(salesOrderReturn.getReturnTaxAmount());
			salesOrderReturnVo.setReturnStatus(salesOrderReturn.getReturnStatus());
			salesOrderReturnVo.setShippingCharge(salesOrderReturn.getShippingCharge());
			salesOrderReturnVo.setIspaid(salesOrderReturn.getIspaid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesOrderReturnVo;
	}
	
	@Override
	public String getSalesOrderReturnRefundRequest(SalesOrderReturnVo salesOrderReturnVo) throws Exception{
		String salesOrderReturnId = salesOrderReturnVo.getSalesOrderReturnId();
		SalesOrderReturn salesReturn = salesOrderReturnDao.getSalesOrderReturnById(salesOrderReturnId);
		SalesOrder salesOrder = salesReturn.getSalesOrder();
		String url = "https://api.secure.ebs.in/api/1_0";
		URL obj;
		HttpsURLConnection con;
		StringBuffer response = new StringBuffer();
		//try {
			obj = new URL(url);
			con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			
			StringBuffer urlParams = new StringBuffer();
			urlParams.append("Action=refund");
			urlParams.append("&AccountID=18984");
			urlParams.append("&SecretKey=2abfa552700f8cf9b9cbdb22909dacfa");
			urlParams.append("&amount=" + salesReturn.getReturnTotalAmount());
			List<Transaction> transactions = transactionDao.getTransactionsByRecordId(salesOrder.getSalesOrderId());
			
			if(transactions.isEmpty() || transactions.get(0)!=null){
				return "Invalid Order Transaction";
			}
			// Once sales order has only one transaction
			String paymentId = transactions.get(0).getPaymentId();
			urlParams.append("&paymentID=" + paymentId);
			
			//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
			//String urlParameters = "Action=refund&AccountID=18984&SecretKey=2abfa552700f8cf9b9cbdb22909dacfa&amount=34.65&paymentID=46931099";
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParams.toString());
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			//System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//print result
			System.out.println(response.toString());
			
		/*} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		return response.toString();
	}

}
