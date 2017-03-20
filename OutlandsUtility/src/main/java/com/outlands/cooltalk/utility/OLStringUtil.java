package com.outlands.cooltalk.utility;

import java.security.SecureRandom;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Service;

@Service
public class OLStringUtil {
	
	/**
	 * Return true if string contains alpha numeric or spaces. 
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isAlphaNumericSpace(String s) {
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == ' ')
				continue;
			
			if (ch >= 'a' && ch <= 'z') 
				continue;
			
			if (ch >= 'A' && ch <= 'Z')
				continue;
			
			if (ch >= '0' && ch <= '9')
				continue;
			
			return false;
				
		}
		
		return true;
	}
	
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
	
	public static String getRandomAlpha(int len) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		
		SecureRandom rnd = new SecureRandom();
		
		StringBuilder bld = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int n = rnd.nextInt(52);
			bld.append(chars.charAt(n));
		}
		
		return bld.toString();
	}
	
	public boolean isEqual(String s1, String s2) {
		
		if (s1 == null && s2 == null) return true;
		
		if (s1 == null || s2 == null) return false;
		
		return s1.equals(s2);
		
	}
}
