package com.outlands.cooltalk.ctDatabase.testUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TMessage;
import com.outlands.cooltalk.ctEntities.entity.TReadBy;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLDateUtils;

@Service
public class TestReadByUtil {
	
	@Autowired
	private OLDateUtils olDateUtils;
	
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Autowired
	private TestMessageUtil testMessageUtil;
	
	public TReadBy createTestReadBy(TUser tUser, TMessage tMessage, String suffix) {
		TReadBy tReadBy = new TReadBy(
				tMessage, 
				tUser,
				new Date());
		
		return tReadBy;
	}

	public  boolean compareReadBy(TReadBy tReadBy1, TReadBy tReadBy2) {
		
		if (!testMessageUtil.compareMessages(tReadBy1.getMessage(), tReadBy2.getMessage())) return false;
		
		if (!testUserUtil.compareUsers(tReadBy1.getUser(), tReadBy2.getUser())) return false;
		
		if (!olDateUtils.isEqual(tReadBy1.getDateRead(), tReadBy2.getDateRead())) return false;
		
		return true;
		
	}
}
