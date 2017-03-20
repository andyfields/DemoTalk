package com.outlands.cooltalk.ctDatabase.testUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TDiscussion;
import com.outlands.cooltalk.ctEntities.entity.TMessageSection;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLDateUtils;
import com.outlands.cooltalk.utility.OLStringUtil;

@Service
public class TestDiscussionUtil {
	
	@Autowired
	private OLStringUtil olStringUtils;
	
	@Autowired
	private OLDateUtils olDateUtils;
	
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Autowired
	private TestMessageSectionUtil testMessageSectionUtil;

	public TDiscussion createTestDiscussion(TUser discussionCreator, TMessageSection messageSection, String suffix) {
		String randChars = OLStringUtil.getRandomAlpha(5);
		TDiscussion tDiscussion = new TDiscussion(
				"Discussion name " + randChars + suffix, 
				"Description " + suffix, 
				new Date(),
				discussionCreator,
				messageSection);
		
		return tDiscussion;
	}

	public  boolean compareDiscussons(TDiscussion tDiscussion1, TDiscussion tDiscussion2) {
		
		if (!olStringUtils.isEqual(tDiscussion1.getName(), tDiscussion2.getName())) return false;
		
		if (!olStringUtils.isEqual(tDiscussion1.getDescription(), tDiscussion2.getDescription())) return false;
		
		if (!olDateUtils.isEqual(tDiscussion1.getCreated(), tDiscussion2.getCreated())) return false;
		
		if (!testUserUtil.compareUsers(tDiscussion1.getDiscussionCreator(), tDiscussion2.getDiscussionCreator())) return false;
		
		if (!testMessageSectionUtil.compareMessageSections(tDiscussion1.getMessageSection(), tDiscussion2.getMessageSection())) return false;
		
		return true;
		
	}
}
