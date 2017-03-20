package com.outlands.cooltalk.utility;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class OLHashUtility {
	
	/**
	 * Takes a salt, plaintext string and returns a hashed version.
	 * 
	 * @param salt
	 * @param sText
	 * @return Hash of given salt and text.
	 */
	public byte[] hash(byte[] salt, String sText) {
		MessageDigest md = null;
		
		byte bText[] = sText.getBytes();
		
		// Concatenate salt and String.
		ByteBuffer buf = ByteBuffer.allocate(salt.length + bText.length);
		buf.put(salt);
		buf.put(bText);
		byte[] mdBuf = buf.array();
		
		try {
			md = MessageDigest.getInstance("SHA"); // step 2
			md.update(mdBuf); // step 3

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

			throw new RuntimeException("Error encrypting password.", e);

		}

		byte raw[] = md.digest();

		return raw;
	}

	public String bytesToString(byte[] byt) {
	   
	    String hash = Base64.encodeBase64URLSafeString(byt); 
	    return hash; 
	}
	
	
	public byte[] stringToBytes(String s) {
		try {
			return Base64.decodeBase64(s);
		} catch (Exception e) {
			return null;
		}
	}
	  
	  
	/**
	 * Generate random bytes.
	 * 
	 * @param len
	 * @return Random byte array of specified length.
	 */
	public byte[] getRandomBuf(int len) {
		Random rand = new Random();

		byte[] buf = new byte[len];
		rand.nextBytes(buf);
		
		return buf;
	}
	
}
