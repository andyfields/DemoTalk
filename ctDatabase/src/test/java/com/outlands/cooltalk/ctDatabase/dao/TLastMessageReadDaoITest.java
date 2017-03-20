package com.outlands.cooltalk.ctDatabase.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctDatabase.service.TDiscussionDataService;
import com.outlands.cooltalk.ctDatabase.service.TLastMessageReadDataService;
import com.outlands.cooltalk.ctDatabase.service.TMessageDataService;
import com.outlands.cooltalk.ctDatabase.service.TMessageSectionDataService;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctDatabase.testUtil.TestDiscussionUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestLastMessageReadUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestMessageSectionUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestMessageUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestUserUtil;
import com.outlands.cooltalk.ctEntities.entity.TDiscussion;
import com.outlands.cooltalk.ctEntities.entity.TLastMessageRead;
import com.outlands.cooltalk.ctEntities.entity.TMessage;
import com.outlands.cooltalk.ctEntities.entity.TMessageSection;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLDateUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:hibernateTestContext.xml"})
public class TLastMessageReadDaoITest {

	@Autowired
	private TDiscussionDataService tDiscussionDataService;
		
	@Autowired
	private TUserDataService tUserDataService;
		
	@Autowired
	private TMessageSectionDataService tMessageSectionDataService;
		
	@Autowired
	private TMessageDataService tMessageDataService;
		
	@Autowired
	private TLastMessageReadDataService tLastMessageReadDataService;
		
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Autowired
	private TestMessageSectionUtil testMessageSectionUtil;

	@Autowired
	private TestDiscussionUtil testDiscussionUtil;

	@Autowired
	private TestMessageUtil testMessageUtil;

	@Autowired
	private TestLastMessageReadUtil testLastMessageReadUtil;
	
	@Autowired
	private OLDateUtils olDateUtils;

	@Test
	public void testTLastMessageRead_create1LastMessageRead_success() {
		
		TUser msgReader = testUserUtil.createTestUser("1");
		tUserDataService.save(msgReader);
		
		TMessageSection section = testMessageSectionUtil.createTestMessageSection("2");
		tMessageSectionDataService.save(section);
		
		TDiscussion tDiscussion = testDiscussionUtil.createTestDiscussion(msgReader, section, "3");
		tDiscussionDataService.save(tDiscussion);
		
		TMessage tMessage = testMessageUtil.createTestMessage(msgReader, tDiscussion, "4");
		tMessageDataService.save(tMessage);
		
		TLastMessageRead tLastMessageRead1 = testLastMessageReadUtil.createTestLastMessageRead(msgReader, tMessage, "4");
		tLastMessageReadDataService.save(tLastMessageRead1);
		
		TLastMessageRead tLastMessageRead2 = tLastMessageReadDataService.get(tLastMessageRead1.getId());
		assertThat(testLastMessageReadUtil.compareLastMessageRead(tLastMessageRead1, tLastMessageRead2)).isTrue();
		
		tLastMessageReadDataService.delete(tLastMessageRead2);
		tMessageDataService.delete(tMessage);
		tDiscussionDataService.delete(tDiscussion);
		tMessageSectionDataService.delete(section);
		tUserDataService.delete(msgReader);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testTLastMessageRead_update1LastMessageRead_success() {
		
		TUser msgReader = testUserUtil.createTestUser("1");
		tUserDataService.save(msgReader);
		
		TMessageSection section = testMessageSectionUtil.createTestMessageSection("2");
		tMessageSectionDataService.save(section);
		
		TDiscussion tDiscussion = testDiscussionUtil.createTestDiscussion(msgReader, section, "3");
		tDiscussionDataService.save(tDiscussion);
		
		TMessage tMessage = testMessageUtil.createTestMessage(msgReader, tDiscussion, "4");
		tMessageDataService.save(tMessage);
		
		TLastMessageRead tLastMessageRead1 = testLastMessageReadUtil.createTestLastMessageRead(msgReader, tMessage, "4");
		tLastMessageReadDataService.save(tLastMessageRead1);
		
		TLastMessageRead tLastMessageRead2 = tLastMessageReadDataService.get(tLastMessageRead1.getId());
		assertThat(testLastMessageReadUtil.compareLastMessageRead(tLastMessageRead1, tLastMessageRead2)).isTrue();
		
		tLastMessageRead2.setLastReadDate(olDateUtils.add(tLastMessageRead2.getLastReadDate(), Calendar.SECOND, 1));
		tLastMessageReadDataService.update(tLastMessageRead2);
		
		TLastMessageRead tLastMessageRead3 = tLastMessageReadDataService.get(tLastMessageRead1.getId());
		
		assertThat(testLastMessageReadUtil.compareLastMessageRead(tLastMessageRead2, tLastMessageRead3)).isTrue();
		assertThat(testLastMessageReadUtil.compareLastMessageRead(tLastMessageRead3, tLastMessageRead1)).isFalse();

		tLastMessageReadDataService.delete(tLastMessageRead2);
		tMessageDataService.delete(tMessage);
		tDiscussionDataService.delete(tDiscussion);
		tMessageSectionDataService.delete(section);
		tUserDataService.delete(msgReader);
		
		System.out.println("Success!");
	}
	
}
