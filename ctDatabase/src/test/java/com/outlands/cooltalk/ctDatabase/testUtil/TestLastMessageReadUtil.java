package com.outlands.cooltalk.ctDatabase.testUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TLastMessageRead;
import com.outlands.cooltalk.ctEntities.entity.TMessage;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLDateUtils;

@Service
public class TestLastMessageReadUtil {
	
	@Autowired
	private OLDateUtils olDateUtils;
	
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Autowired
	private TestMessageUtil testMessageUtil;
	
	public TLastMessageRead createTestLastMessageRead(TUser tUser, TMessage tMessage, String suffix) {
		TLastMessageRead tLastRead = new TLastMessageRead(
				tUser,
				tMessage, 
				new Date());
		
		return tLastRead;
	}

	public  boolean compareLastMessageRead(TLastMessageRead tLastRead1, TLastMessageRead tLastRead2) {
		
		if (!testMessageUtil.compareMessages(tLastRead1.getMessage(), tLastRead2.getMessage())) return false;
		
		if (!testUserUtil.compareUsers(tLastRead1.getUser(), tLastRead2.getUser())) return false;
		
		if (!olDateUtils.isEqual(tLastRead1.getLastReadDate(), tLastRead2.getLastReadDate())) return false;
		
		return true;
		
	}
}
