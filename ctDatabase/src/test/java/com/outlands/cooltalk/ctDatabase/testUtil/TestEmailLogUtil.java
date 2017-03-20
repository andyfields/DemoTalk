package com.outlands.cooltalk.ctDatabase.testUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TEmailLog;
import com.outlands.cooltalk.utility.OLStringUtil;

@Service
public class TestEmailLogUtil {
	
	@Autowired
	private OLStringUtil olStringUtils;

	public TEmailLog createTestEmailLog(String suffix) {
		String randChars = OLStringUtil.getRandomAlpha(5);
		TEmailLog tEmailLog = new TEmailLog();
		tEmailLog.setServerId("serverID" + randChars + suffix);
		tEmailLog.setToAdrs("toAdrs" + randChars + suffix + "@email.com");
		tEmailLog.setFromAdrs("fromAdrs" + randChars + suffix + "@email.com");
		tEmailLog.setReplyToAdrs("replyToAdrs" + randChars + suffix + "@email.com");
		tEmailLog.setSubject("subject" + randChars + suffix);
		tEmailLog.setContent("content" + randChars + suffix);
		
		return tEmailLog;
	}

	public  boolean compareEmailLog(TEmailLog emailLog1, TEmailLog emailLog2) {
		
		if (!olStringUtils.isEqual(emailLog1.getServerId(), emailLog2.getServerId())) return false;
		
		if (!olStringUtils.isEqual(emailLog1.getToAdrs(), emailLog2.getToAdrs())) return false;
		
		if (!olStringUtils.isEqual(emailLog1.getReplyToAdrs(), emailLog2.getReplyToAdrs())) return false;
		
		if (!olStringUtils.isEqual(emailLog1.getFromAdrs(), emailLog2.getFromAdrs())) return false;
		
		if (!olStringUtils.isEqual(emailLog1.getSubject(), emailLog2.getSubject())) return false;
		
		if (!olStringUtils.isEqual(emailLog1.getContent(), emailLog2.getContent())) return false;
		
		return true;
		
	}
}
