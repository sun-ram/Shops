/**
 * 
 */
package com.mitosis.shopsbacker.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Anbukkani Gajendiran
 *
 */
public final class SBSecurity {

	
	public static String encrypt(String text){
		 String encryptedString=null;
		try{
         String key = "Bar12345Bar12345"; // 128 bit key

         // Create key and cipher
         Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
         Cipher cipher = Cipher.getInstance("AES");
 
         // encrypt the text
         cipher.init(Cipher.ENCRYPT_MODE, aesKey);
         byte[] encrypted = cipher.doFinal(text.getBytes());
          encryptedString =new String(encrypted);
         // decrypt the text
		}catch(Exception e){
			
		}
		return encryptedString;
	}
	
	public static String decrypt(String text){
		 String decrypted=null;
		try{
        String key = "Bar12345Bar12345"; // 128 bit key

        // Create key and cipher
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        byte[] encrypted = text.getBytes();

        // decrypt the text
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
          decrypted = new String(cipher.doFinal(encrypted));
		}catch(Exception e){
			
		}
		return decrypted;
	}
}
