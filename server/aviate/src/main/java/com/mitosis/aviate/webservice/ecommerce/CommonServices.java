package com.mitosis.aviate.webservice.ecommerce;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("get")
public class CommonServices {
	@Path("payment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject makePayment(JSONObject payment) throws JSONException {
		JSONObject payList = new JSONObject();
		try {
			String transctionKey = "9P6uPlIjA0E95C945T4k";
			String paymentPageId = "WSP-MITOS-E2mIIgBi3g";
			String sequenceNumber = "123456";// temp hardcode
			long timestamp = System.currentTimeMillis() / 1000L;
			/*String amount = "1.22";*/
			String amount = payment.getString("amount");
			String currencyCode = "INR";// tem hardcode
			String encryptiontype = "HmacMD5";
			String msg = paymentPageId + "^" + sequenceNumber + "^" + timestamp
					+ "^" + amount + "^" + currencyCode;// 76aa0e9c6f895563f0bd5af54101e77c
			String hashMessage = hmacDigest(msg, transctionKey, encryptiontype);
			payList.put("transctionKey", transctionKey);
			payList.put("paymentPageId", paymentPageId);
			payList.put("sequenceNumber", sequenceNumber);
			payList.put("timestamp", timestamp);
			payList.put("currencyCode",currencyCode);
			payList.put("hashMessage",hashMessage);
			payList.put("status", com.mitosis.aviate.util.Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			payList.put("status", com.mitosis.aviate.util.Constants.FAILURE);
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
}
