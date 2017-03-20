package com.outlands.cooltalk.ctDatabase.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctDatabase.service.TDiscussionDataService;
import com.outlands.cooltalk.ctDatabase.service.TMessageDataService;
import com.outlands.cooltalk.ctDatabase.service.TMessageSectionDataService;
import com.outlands.cooltalk.ctDatabase.service.TReadByDataService;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctDatabase.testUtil.TestDiscussionUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestMessageSectionUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestMessageUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestReadByUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestUserUtil;
import com.outlands.cooltalk.ctEntities.entity.TDiscussion;
import com.outlands.cooltalk.ctEntities.entity.TMessage;
import com.outlands.cooltalk.ctEntities.entity.TMessageSection;
import com.outlands.cooltalk.ctEntities.entity.TReadBy;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLDateUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:hibernateTestContext.xml"})
public class TReadByDaoITest {

	@Autowired
	private TDiscussionDataService tDiscussionDataService;
		
	@Autowired
	private TUserDataService tUserDataService;
		
	@Autowired
	private TMessageSectionDataService tMessageSectionDataService;
		
	@Autowired
	private TMessageDataService tMessageDataService;
		
	@Autowired
	private TReadByDataService tReadByDataService;
		
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Autowired
	private TestMessageSectionUtil testMessageSectionUtil;

	@Autowired
	private TestDiscussionUtil testDiscussionUtil;

	@Autowired
	private TestMessageUtil testMessageUtil;

	@Autowired
	private TestReadByUtil testReadByUtil;
	
	@Autowired
	private OLDateUtils olDateUtils;

	@Test
	public void testTLastReadBy_create1TLastReadBy_success() {
		
		TUser msgReader = testUserUtil.createTestUser("1");
		tUserDataService.save(msgReader);
		
		TMessageSection section = testMessageSectionUtil.createTestMessageSection("2");
		tMessageSectionDataService.save(section);
		
		TDiscussion tDiscussion = testDiscussionUtil.createTestDiscussion(msgReader, section, "3");
		tDiscussionDataService.save(tDiscussion);
		
		TMessage tMessage = testMessageUtil.createTestMessage(msgReader, tDiscussion, "4");
		tMessageDataService.save(tMessage);
		
		TReadBy tReadBy1 = testReadByUtil.createTestReadBy(msgReader, tMessage, "4");
		tReadByDataService.save(tReadBy1);
		
		TReadBy tReadBy2 = tReadByDataService.get(tReadBy1.getId());
		assertThat(testReadByUtil.compareReadBy(tReadBy1, tReadBy2)).isTrue();
		
		tReadByDataService.delete(tReadBy2);
		tMessageDataService.delete(tMessage);
		tDiscussionDataService.delete(tDiscussion);
		tMessageSectionDataService.delete(section);
		tUserDataService.delete(msgReader);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testTLastReadBy_update1LastReadBy_success() {
		
		TUser msgReader = testUserUtil.createTestUser("1");
		tUserDataService.save(msgReader);
		
		TMessageSection section = testMessageSectionUtil.createTestMessageSection("2");
		tMessageSectionDataService.save(section);
		
		TDiscussion tDiscussion = testDiscussionUtil.createTestDiscussion(msgReader, section, "3");
		tDiscussionDataService.save(tDiscussion);
		
		TMessage tMessage = testMessageUtil.createTestMessage(msgReader, tDiscussion, "4");
		tMessageDataService.save(tMessage);
		
		TReadBy tReadBy1 = testReadByUtil.createTestReadBy(msgReader, tMessage, "4");
		tReadByDataService.save(tReadBy1);
		
		TReadBy tReadBy2 = tReadByDataService.get(tReadBy1.getId());
		assertThat(testReadByUtil.compareReadBy(tReadBy1, tReadBy2)).isTrue();
		
		tReadBy2.setDateRead(olDateUtils.add(tReadBy2.getDateRead(), Calendar.SECOND, 1));
		tReadByDataService.update(tReadBy2);
		
		TReadBy tReadBy3 = tReadByDataService.get(tReadBy1.getId());
		
		assertThat(testReadByUtil.compareReadBy(tReadBy2, tReadBy3)).isTrue();
		assertThat(testReadByUtil.compareReadBy(tReadBy3, tReadBy1)).isFalse();

		tReadByDataService.delete(tReadBy2);
		tMessageDataService.delete(tMessage);
		tDiscussionDataService.delete(tDiscussion);
		tMessageSectionDataService.delete(section);
		tUserDataService.delete(msgReader);
		
		System.out.println("Success!");
	}
	
}
