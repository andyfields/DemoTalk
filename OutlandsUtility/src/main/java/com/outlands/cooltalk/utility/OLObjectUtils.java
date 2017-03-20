package com.outlands.cooltalk.utility;

import org.springframework.stereotype.Service;

@Service
/**
 * Various useful date utilities.
 * 
 * @author Andy fields
 *
 */
public class OLObjectUtils {
	
	/**
	 * Null safe comparison of two objects where equals is defined.
	 * 
	 * @param o1
	 * @param 02
	 * @return
	 */
	public boolean isEqual(Object o1, Object o2) {
		
		if (o1 == null && o2 == null) return true;
		
		if (o1 == null || o2 == null) return false;
		
		return (o1.equals(o2));	
	}
	
	
}
