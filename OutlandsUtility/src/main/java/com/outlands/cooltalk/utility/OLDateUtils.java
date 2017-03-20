package com.outlands.cooltalk.utility;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
/**
 * Various useful date utilities.
 * 
 * @author Andy fields
 *
 */
public class OLDateUtils {
	
	/**
	 * Given a start time, a Calendar unit type, and a count of time units, is the current time after the 
	 * expiration time?
	 * 
	 * @param startTime
	 * @param calendarUnit
	 * @param timeUnits
	 * @return If time is expired.
	 */
	public boolean isExpired(Date startTime, int calendarUnit, int timeUnits) {
		Date expire = add(startTime, calendarUnit, timeUnits);
		return getNow().after(expire);
	}

	public boolean isEqual(LocalDateTime dt1, LocalDateTime dt2) {
		
		if (dt1 == null && dt2 == null) return true;
		
		if (dt1 == null || dt2 == null) return false;
		
		return (dt1.equals(dt2));	
	}
	
	public Date getNow() {
		return new Date();
	}
	
	public Date add(Date dt, int calendarUnit, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(calendarUnit, amount);
		return cal.getTime();
	}
	
	// True if dateToCompare is >= startRange and < endRange.
	public boolean inRange(Date dateToCompare, Date startRange, Date endRange) {
		
		return (dateToCompare.getTime() >= startRange.getTime() && dateToCompare.getTime() < endRange.getTime());
		
	}
	
	public boolean isEqual(Date dt1, Date dt2) {
		
		if (dt1 == null && dt2 == null) return true;
		
		if (dt1 == null || dt2 == null) return false;
		
		/* We do not use Date.equals() here.  util.Date has a number of subclasses including Timestamp and
		 * sql.Date.  If the two objects are different classes then .equals() may not come back as true
		 * even though the dates are the same and classes are all subclasses of util.Date.
		 */
		return (dt1.getTime() == dt2.getTime());
		
	}
	
}
