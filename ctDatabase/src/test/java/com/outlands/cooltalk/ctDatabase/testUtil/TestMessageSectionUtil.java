package com.outlands.cooltalk.ctDatabase.testUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TMessageSection;
import com.outlands.cooltalk.utility.OLDateUtils;
import com.outlands.cooltalk.utility.OLStringUtil;

@Service
public class TestMessageSectionUtil {
	
	@Autowired
	private OLStringUtil olStringUtils;
	
	@Autowired
	private OLDateUtils olDateUtils;

	public TMessageSection createTestMessageSection(String suffix) {
		String randChars = OLStringUtil.getRandomAlpha(5);
		TMessageSection tMessageSection = new TMessageSection("Section name " + randChars + suffix, new Date());
		
		return tMessageSection;
	}

	public  boolean compareMessageSections(TMessageSection sec1, TMessageSection sec2) {
		
		if (!olStringUtils.isEqual(sec1.getName(), sec2.getName())) return false;
		
		return olDateUtils.isEqual(sec1.getCreated(), sec2.getCreated());
		
	}
}
