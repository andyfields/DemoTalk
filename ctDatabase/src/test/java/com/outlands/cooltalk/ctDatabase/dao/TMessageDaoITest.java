package com.outlands.cooltalk.ctDatabase.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctDatabase.service.TDiscussionDataService;
import com.outlands.cooltalk.ctDatabase.service.TMessageDataService;
import com.outlands.cooltalk.ctDatabase.service.TMessageSectionDataService;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctDatabase.testUtil.TestDiscussionUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestMessageSectionUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestMessageUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestUserUtil;
import com.outlands.cooltalk.ctEntities.entity.TDiscussion;
import com.outlands.cooltalk.ctEntities.entity.TMessage;
import com.outlands.cooltalk.ctEntities.entity.TMessageSection;
import com.outlands.cooltalk.ctEntities.entity.TUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:hibernateTestContext.xml"})
public class TMessageDaoITest {

	@Autowired
	private TDiscussionDataService tDiscussionDataService;
		
	@Autowired
	private TUserDataService tUserDataService;
		
	@Autowired
	private TMessageSectionDataService tMessageSectionDataService;
		
	@Autowired
	private TMessageDataService tMessageDataService;
		
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Autowired
	private TestMessageSectionUtil testMessageSectionUtil;

	@Autowired
	private TestDiscussionUtil testDiscussionUtil;

	@Autowired
	private TestMessageUtil testMessageUtil;

	@Test
	public void testTMessage_create1Message_success() {
		
		TUser creator = testUserUtil.createTestUser("1");
		tUserDataService.save(creator);
		
		TMessageSection section = testMessageSectionUtil.createTestMessageSection("2");
		tMessageSectionDataService.save(section);
		
		TDiscussion tDiscussion = testDiscussionUtil.createTestDiscussion(creator, section, "3");
		tDiscussionDataService.save(tDiscussion);
		
		TMessage tMessage1 = testMessageUtil.createTestMessage(creator, tDiscussion, "4");
		tMessageDataService.save(tMessage1);
		
		TMessage tMessage2 =  tMessageDataService.get(tMessage1.getId());
		assertThat(testMessageUtil.compareMessages(tMessage1, tMessage2)).isTrue();
		
		tMessageDataService.delete(tMessage2);
		tDiscussionDataService.delete(tDiscussion);
		tMessageSectionDataService.delete(section);
		tUserDataService.delete(creator);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testTMessage_update1Message_success() {
		
		TUser creator = testUserUtil.createTestUser("1");
		tUserDataService.save(creator);
		
		TMessageSection section = testMessageSectionUtil.createTestMessageSection("2");
		tMessageSectionDataService.save(section);
		
		TDiscussion tDiscussion = testDiscussionUtil.createTestDiscussion(creator, section, "3");
		tDiscussionDataService.save(tDiscussion);
		
		TMessage tMessage1 = testMessageUtil.createTestMessage(creator, tDiscussion, "4");
		tMessageDataService.save(tMessage1);
		
		TMessage tMessage2 =  tMessageDataService.get(tMessage1.getId());
		assertThat(testMessageUtil.compareMessages(tMessage1, tMessage2)).isTrue();
				
		tMessage2.setMessage("New Message");
		tMessageDataService.update(tMessage2);
		
		TMessage tMessage3 = tMessageDataService.get(tMessage1.getId());
		
		assertThat(testMessageUtil.compareMessages(tMessage2, tMessage3)).isTrue();
		assertThat(testMessageUtil.compareMessages(tMessage3, tMessage1)).isFalse();

		tMessageDataService.delete(tMessage2);
		tDiscussionDataService.delete(tDiscussion);
		tMessageSectionDataService.delete(section);
		tUserDataService.delete(creator);
		
		System.out.println("Success!");
	}
	
}
