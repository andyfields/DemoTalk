package com.outlands.cooltalk.ctDatabase.testUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TDiscussion;
import com.outlands.cooltalk.ctEntities.entity.TMessage;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLDateUtils;
import com.outlands.cooltalk.utility.OLStringUtil;

@Service
public class TestMessageUtil {
	
	@Autowired
	private OLStringUtil olStringUtils;
	
	@Autowired
	private OLDateUtils olDateUtils;
	
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Autowired
	private TestDiscussionUtil testDiscussionUtil;
	
	public TMessage createTestMessage(TUser discussionCreator, TDiscussion discussion, String suffix) {
		TMessage tMessage = new TMessage(
				discussion,
				discussionCreator, 
				"Message " + suffix, 
				null,
				new Date(),
				"DisplayId");
		
		return tMessage;
	}

	public  boolean compareMessages(TMessage tMsg1, TMessage tMsg2) {
		
		if (!testDiscussionUtil.compareDiscussons(tMsg1.getDiscussion(), tMsg2.getDiscussion())) return false;
		
		if (!testUserUtil.compareUsers(tMsg1.getCreator(), tMsg2.getCreator())) return false;
		
		if (!olStringUtils.isEqual(tMsg1.getMessage(), tMsg2.getMessage())) return false;
		
		if (!olDateUtils.isEqual(tMsg1.getDateCreated(), tMsg2.getDateCreated())) return false;
		
		if (!olStringUtils.isEqual(tMsg1.getDisplayId(), tMsg2.getDisplayId())) return false;
		
		return true;
		
	}
}
