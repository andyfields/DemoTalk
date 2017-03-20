package com.outlands.cooltalk.ctDatabase.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctDatabase.service.TMessageSectionDataService;
import com.outlands.cooltalk.ctDatabase.testUtil.TestMessageSectionUtil;
import com.outlands.cooltalk.ctEntities.entity.TMessageSection;
import com.outlands.cooltalk.utility.OLStringUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:hibernateTestContext.xml"})
public class TMessageSectionDaoITest {

	@Autowired
	private TMessageSectionDataService tMessageSectionDataService;
	
	@Autowired
	private TestMessageSectionUtil testMessageSectionUtil;
	
	@Test
	public void testTMessageSection_create1Section_success() {
		TMessageSection tMessageSection1 = testMessageSectionUtil.createTestMessageSection("1");
		tMessageSectionDataService.save(tMessageSection1);
		
		TMessageSection tMessageSection2 = tMessageSectionDataService.get(tMessageSection1.getId());
		
		assertThat(testMessageSectionUtil.compareMessageSections(tMessageSection1, tMessageSection2)).isTrue();
		
		tMessageSectionDataService.delete(tMessageSection1);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testTMessageSection_update1Section_success() {
		TMessageSection tMessageSection1 = testMessageSectionUtil.createTestMessageSection("1");
		tMessageSectionDataService.save(tMessageSection1);

		TMessageSection tMessageSection2 = tMessageSectionDataService.get(tMessageSection1.getId());
		
		assertThat(testMessageSectionUtil.compareMessageSections(tMessageSection1, tMessageSection2)).isTrue();
		
		tMessageSection2.setName("New section name " + OLStringUtil.getRandomAlpha(5));
		tMessageSectionDataService.update(tMessageSection2);
		
		TMessageSection tMessageSection3 = tMessageSectionDataService.get(tMessageSection1.getId());
		
		assertThat(testMessageSectionUtil.compareMessageSections(tMessageSection2, tMessageSection3)).isTrue();
		assertThat(testMessageSectionUtil.compareMessageSections(tMessageSection3, tMessageSection1)).isFalse();

		tMessageSectionDataService.delete(tMessageSection3);
		
		System.out.println("Success!");
	}
	
	
}
