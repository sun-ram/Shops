package com.mitosis.aviate.firstdata.integration;

import java.math.BigDecimal;
import java.net.URL;

import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.codehaus.jettison.json.JSONObject;

import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.Amount;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.Card;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.CardCodeValue;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.CardNumber_type1;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.ChargeTotal_type1;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.CreditCardData;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.CreditCardDataChoice_type0;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.CreditCardDataSequence_type0;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.CreditCardTxType;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.ExpMonth_type1;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.ExpYear_type1;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.FDGGWSApiOrderRequest;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.FDGGWSApiOrderResponse;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.Payment;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.Payment_type0;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.Transaction;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.TransactionChoice_type0;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.TransactionSequence_type0;
import com.mitosis.aviate.firstdata.integration.FDGGWSApiOrderServiceStub.Type_type1;



public class FDGGWSAxisClient {
	public JSONObject creditCard(JSONObject reqObj) { 
		
		try{
		 	final String keyStorePassword="JBb4zZBWx9";
		 	final String authUsername="WS1909377130._.1";
		 	final String authPassword="nQYNWnQm";


		URL path = this.getClass().getResource("/sslKey/WS1909377130._.1.ks");
		System.out.println(path);
		String url=path.toString();
		System.out.println(url);
		
		System.setProperty("javax.net.ssl.keyStore", url); 
		System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword); 
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator(); 
		auth.setPreemptiveAuthentication(true); 
		//the following username and password values come from the WS<store name>._.1.auth.txt file 
		//which is found inside the store CERT files archive that may be download via Virtual Terminal (under Support - Download Center) 
		auth.setUsername(authUsername); 
		auth.setPassword(authPassword); 

		FDGGWSApiOrderServiceStub fdggWsStub = new FDGGWSApiOrderServiceStub(); 
		  
		
		//----------------------
		//String cardNo="4111111111111111";//sample credit card num
		//String expDate="03/16";//jsonsent.getString("expDate");
		//Double amountToPaid=13.45;//jsonsent.getDouble("amount");
		//String ccvValue="236";//jsonsent.getString("ccv");
		//String date[]=expDate.split("/");
		
		String cardNo = reqObj.getString("cardNo");//sample credit card num
		String expDate = reqObj.getString("expDate");//jsonsent.getString("expDate");
		Double amountToPaid = Double.parseDouble(reqObj.getString("amountToPaid"));//jsonsent.getDouble("amount");
		String ccvValue = reqObj.getString("ccvValue");//jsonsent.getString("ccv");
		String date[]=expDate.split("/");
		
		//Double amountToPaid=10.0;//jsonsent.getDouble("amount");
		BigDecimal paidAmount=new BigDecimal(amountToPaid);
		BigDecimal amountPaid=paidAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		org.apache.axis2.client.Options options = fdggWsStub._getServiceClient().getOptions();
		options.setProperty(HTTPConstants.AUTHENTICATE, auth); 
		Type_type1 sale_type = Type_type1.value6; 
		CreditCardTxType creditCardTxType = new CreditCardTxType(); 
		creditCardTxType.setType(sale_type); 
		CardNumber_type1 cardNumber_type1 = new CardNumber_type1(); 
		cardNumber_type1.setCardNumber_type0(cardNo); 
		ExpMonth_type1 expMonth_type1 = new ExpMonth_type1(); 
		expMonth_type1.setExpMonth_type0(date[0]); 		
		ExpYear_type1 expYear_type1 = new ExpYear_type1(); 
		expYear_type1.setExpYear_type0(date[1]); 
		Card card = new Card(); 
		card.setCardNumber(cardNumber_type1); 
		card.setExpMonth(expMonth_type1); 
		card.setExpYear(expYear_type1);
		
		CreditCardDataSequence_type0 creditCardDataSequence_type0 = new CreditCardDataSequence_type0(); 
		creditCardDataSequence_type0.setCard(card);
		
		CardCodeValue ccv=new CardCodeValue();
		ccv.setCardCodeValue(ccvValue);
		creditCardDataSequence_type0.setCardCodeValue(ccv);
		
		CreditCardDataChoice_type0 dataChoice = new CreditCardDataChoice_type0(); 
		dataChoice.setCreditCardDataSequence_type0(creditCardDataSequence_type0); 
		CreditCardData creditCardData = new CreditCardData(); 
		creditCardData.setCreditCardDataChoice_type0(dataChoice); 
		BigDecimal bigDecimal =amountPaid; 
		ChargeTotal_type1 chargeTotal_type1 = new ChargeTotal_type1(); 
		chargeTotal_type1.setChargeTotal_type0(bigDecimal); 
		Amount amount = new Amount(); 
		amount.setChargeTotal(chargeTotal_type1); 
		Payment_type0 payment_type0 = new Payment_type0(); 
		payment_type0.setAmount(amount); 
		Payment payment = new Payment();
		payment.setPayment(payment_type0); 
		
		 TransactionSequence_type0 transactionSequence_type0 = new TransactionSequence_type0(); 
		transactionSequence_type0.setCreditCardTxType(creditCardTxType); 
		transactionSequence_type0.setCreditCardData(creditCardData); 
		TransactionChoice_type0 transactionChoice_type0= new TransactionChoice_type0(); 
		transactionChoice_type0.setTransactionSequence_type0(transactionSequence_type0); 
		Transaction t = new Transaction(); 
		t.setTransactionChoice_type0(transactionChoice_type0); 
		t.setPayment(payment_type0); 
		FDGGWSApiOrderRequest fdggwsApiOrderRequest = new FDGGWSApiOrderRequest(); 
		fdggwsApiOrderRequest.setTransaction(t); 
		FDGGWSApiOrderResponse response = fdggWsStub.fDGGWSApiOrder(fdggwsApiOrderRequest);
		String ccvParameValue=t.getTransactionChoice_type0().getTransactionSequence_type0().getCreditCardData().getCreditCardDataChoice_type0().getCreditCardDataSequence_type0().getCardCodeValue().getCardCodeValue();

		 JSONObject respObject = new JSONObject();
		
	    respObject.put("origAmount", "12.30");  
	    respObject.put("enoughCredit", false);
	    respObject.put("amount", t.getPayment().getAmount().getChargeTotal().toString());
	    respObject.put("status", response.getTransactionResult());
	    respObject.put("transactionId", response.getTransactionID());
	    respObject.put("approvalNo", response.getApprovalCode());
	    respObject.put("errorMessage", response.getErrorMessage());
	    respObject.put("cardNo", t.getTransactionChoice_type0().getTransactionSequence_type0().getCreditCardData().getCreditCardDataChoice_type0().getCreditCardDataSequence_type0().getCard().getCardNumber());
	    System.out.println( response.getApprovalCode());
	    return respObject;

	   
	}catch(Exception e){
		e.printStackTrace();
	}
	finally{
//		OBContext.restorePreviousMode();
//		return resultObj;
		
	}
		return reqObj;
}
}

    
	

