package com.mitosis.shopsbacker.webservice;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.util.HashGeneratorUtils;
import com.mitosis.shopsbacker.util.SBMessageStatus;

@Path("payment")
@Controller("paymentGatewayRestService")
public class PaymentGatewayRestService<T> {
	
	@Path("/gatewaydetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JSONObject getGatewayDetails(JSONObject payment) throws JSONException {
		JSONObject payList = new JSONObject();
		try {
			String secret_key = "2abfa552700f8cf9b9cbdb22909dacfa";
			String message =  secret_key+"|"+"18984|Gokul nagar|MD5|100.00|0|Tambaram|IND|INR|INR|"
					+ "Test Transaction|prabakaran.a@mitosistech.com|"
					+ "LIVE|PRABAKARAN|8489241198|600073|PRABAKARAN123456789|http://localhost:8088/aviate/transaction|"
					+ "Gokul nagar|chennai|IND|Mitosistech|8489241198|600073|Tamil Nadu|Tamil Nadu";
			
			
			
			message = "2abfa552700f8cf9b9cbdb22909dacfa|18984|tambaram, tambaram|MD5|1442|0"
					+ "|Chennai (Madras)|IND|INR|INR|ff80818151e788c80151e78a8a920012|prabakaran.a@mitosistech.com"
					+ "|LIVE|Prabakaran|8489241198|600073|PRABA10075KARAN|http://localhost:8088/aviate/transaction|"
					+ "tambaram, tambaram|Chennai (Madras)|"
					+ "IND|praba23|8489241198|600073|Tamil Nadu|Tamil Nadu";
			String hashMessage = HashGeneratorUtils.generateMD5(message);
			System.out.println("hashMessage ------ "+hashMessage.toUpperCase());
			payList.put("transctionKey", secret_key);
			payList.put("hashMessage",hashMessage.toUpperCase());
			payList.put("status", SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			payList.put("status", SBMessageStatus.FAILURE.getValue());
			payList.put("errorString", "");
			payList.put("errorCode", "");
		}

		return payList;
	}
	
	public static String hmacDigest(String msg, String keyString, String algo) {
		String digest = null;
		try {
			SecretKeySpec key = new SecretKeySpec(
					(keyString).getBytes("UTF-8"), algo);
			Mac mac = Mac.getInstance(algo);
			mac.init(key);

			byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

			StringBuffer hash = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(0xFF & bytes[i]);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			digest = hash.toString();
		} catch (UnsupportedEncodingException e) {
		} catch (InvalidKeyException e) {
		} catch (NoSuchAlgorithmException e) {
		}
		return digest;
	}
	
	/*public static String md5Digest(String msg, String keyString, String algo) {
		String digest = null;
		try {
			SecretKeySpec key = new SecretKeySpec(
					(keyString).getBytes("UTF-8"), algo);
			Mac mac = Mac.getInstance(algo);
			mac.init(key);

			byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

			StringBuffer hash = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(0xFF & bytes[i]);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			digest = hash.toString();
		} catch (UnsupportedEncodingException e) {
		} catch (InvalidKeyException e) {
		} catch (NoSuchAlgorithmException e) {
		}
		return digest;
	}*/

}
