package com.outlands.cooltalk.ctDatabase.testUtil;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TSuspensionDetail;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLDateUtils;
import com.outlands.cooltalk.utility.OLStringUtil;

@Service
public class TestSuspensionDetailUtil {
	
	@Autowired
	private OLStringUtil olStringUtils;
	
	@Autowired
	private OLDateUtils olDateUtils;
	
	@Autowired
	private TestUserUtil testUserUtils;

	public TSuspensionDetail createTestSuspensionDetail(TUser user, String suffix) {
		String randChars = OLStringUtil.getRandomAlpha(5);
		TSuspensionDetail tSuspensionDetail = new TSuspensionDetail(user, 
				"Reason " + randChars + suffix, 
				new Date(),
				olDateUtils.add(new Date(), Calendar.DATE, 30));
		
		return tSuspensionDetail;
	}

	public  boolean compareSuspensionDetails(TSuspensionDetail susp1, TSuspensionDetail susp2) {
		
		if (!testUserUtils.compareUsers(susp1.getUser(), susp2.getUser())) return false;
		
		if (!olStringUtils.isEqual(susp1.getReason(), susp2.getReason())) return false;
		
		if  (!olDateUtils.isEqual(susp1.getStartDate(), susp2.getStartDate())) return false;
		
		if  (!olDateUtils.isEqual(susp1.getEndDate(), susp2.getEndDate())) return false;
		
		return true;
		
	}
}
