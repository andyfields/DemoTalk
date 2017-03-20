package com.outlands.cooltalk.ctDatabase.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctDatabase.service.TDiscussionDataService;
import com.outlands.cooltalk.ctDatabase.service.TMessageSectionDataService;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctDatabase.testUtil.TestDiscussionUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestMessageSectionUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestUserUtil;
import com.outlands.cooltalk.ctEntities.entity.TDiscussion;
import com.outlands.cooltalk.ctEntities.entity.TMessageSection;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLStringUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:hibernateTestContext.xml"})
public class TDiscussionDaoITest {

	@Autowired
	private TDiscussionDataService tDiscussionDataService;
		
	@Autowired
	private TUserDataService tUserDataService;
		
	@Autowired
	private TMessageSectionDataService tMessageSectionDataService;
		
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Autowired
	private TestMessageSectionUtil testMessageSectionUtil;

	@Autowired
	private TestDiscussionUtil testDiscussionUtil;

	@Test
	public void testTDiscussion_create1Discussion_success() {
		
		TUser creator = testUserUtil.createTestUser("1");
		tUserDataService.save(creator);
		
		TMessageSection section = testMessageSectionUtil.createTestMessageSection("2");
		tMessageSectionDataService.save(section);
		
		TDiscussion tDiscussion1 = testDiscussionUtil.createTestDiscussion(creator, section, "3");
		tDiscussionDataService.save(tDiscussion1);
		
		TDiscussion tDiscussion2 = tDiscussionDataService.get(tDiscussion1.getId());
		
		assertThat(testDiscussionUtil.compareDiscussons(tDiscussion1, tDiscussion2)).isTrue();
		
		tDiscussionDataService.delete(tDiscussion2);
		tMessageSectionDataService.delete(section);
		tUserDataService.delete(creator);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testTDiscussion_update1Discussion_success() {
		TUser creator = testUserUtil.createTestUser("1");
		tUserDataService.save(creator);
		
		TMessageSection section = testMessageSectionUtil.createTestMessageSection("2");
		tMessageSectionDataService.save(section);
		
		TDiscussion tDiscussion1 = testDiscussionUtil.createTestDiscussion(creator, section, "3");
		tDiscussionDataService.save(tDiscussion1);
		
		TDiscussion tDiscussion2 = tDiscussionDataService.get(tDiscussion1.getId());
		
		assertThat(testDiscussionUtil.compareDiscussons(tDiscussion1, tDiscussion2)).isTrue();
		
		tDiscussion2.setDescription("New Discussion Name " + OLStringUtil.getRandomAlpha(5));
		tDiscussionDataService.update(tDiscussion2);
		
		TDiscussion tDiscussion3 = tDiscussionDataService.get(tDiscussion1.getId());
		
		assertThat(testDiscussionUtil.compareDiscussons(tDiscussion2, tDiscussion3)).isTrue();
		assertThat(testDiscussionUtil.compareDiscussons(tDiscussion3, tDiscussion1)).isFalse();

		tDiscussionDataService.delete(tDiscussion2);
		tMessageSectionDataService.delete(section);
		tUserDataService.delete(creator);
		
		System.out.println("Success!");
	}
	
}
