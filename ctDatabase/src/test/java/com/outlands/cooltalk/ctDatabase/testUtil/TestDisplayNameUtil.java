package com.outlands.cooltalk.ctDatabase.testUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TDisplayName;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLDateUtils;
import com.outlands.cooltalk.utility.OLStringUtil;

@Service
public class TestDisplayNameUtil {
	
	@Autowired
	private OLStringUtil olStringUtils;
	
	@Autowired
	private OLDateUtils olDateUtils;
	
	public TDisplayName createDisplayName(TUser tUser, String suffix) {
		
		String randChars = OLStringUtil.getRandomAlpha(5);
		TDisplayName tDisplayName = new TDisplayName(tUser, "Display Name " + randChars + suffix, new Date());
		
		return tDisplayName;
	}

	public boolean compareDisplayNames(TDisplayName name1, TDisplayName name2) {
		
		if (!olStringUtils.isEqual(name1.getDisplayName(), name2.getDisplayName())) return false;
		
		if (!olDateUtils.isEqual(name1.getStartDate(), name2.getStartDate())) return false;
		
		return olDateUtils.isEqual(name1.getStartDate(), name2.getStartDate());
		
	}
}
