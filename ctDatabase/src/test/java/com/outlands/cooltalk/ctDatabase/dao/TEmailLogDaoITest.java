package com.outlands.cooltalk.ctDatabase.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctDatabase.service.TEmailLogDataService;
import com.outlands.cooltalk.ctDatabase.testUtil.TestEmailLogUtil;
import com.outlands.cooltalk.ctEntities.entity.TEmailLog;
import com.outlands.cooltalk.utility.OLStringUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:hibernateTestContext.xml"})
public class TEmailLogDaoITest {

	@Autowired
	private TEmailLogDataService tEmailLogDataService;
	
	@Autowired
	private TestEmailLogUtil testEmailLogUtil;
	
	@Test
	public void testTEmailLog_create_success() {
		TEmailLog tEmailLog1 = testEmailLogUtil.createTestEmailLog("1");
		
		tEmailLogDataService.save(tEmailLog1);
		TEmailLog tEmailLog2 = tEmailLogDataService.get(tEmailLog1.getId());
		
		assertThat(testEmailLogUtil.compareEmailLog(tEmailLog1, tEmailLog2)).isTrue();
		
		tEmailLogDataService.delete(tEmailLog1);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testTEmailLog_update_success() {
		TEmailLog tEmailLog1 = testEmailLogUtil.createTestEmailLog("1");
		
		tEmailLogDataService.save(tEmailLog1);
		TEmailLog tEmailLog2 = tEmailLogDataService.get(tEmailLog1.getId());
		
		assertThat(testEmailLogUtil.compareEmailLog(tEmailLog1, tEmailLog2)).isTrue();
		
		tEmailLog2.setSubject("New subject " + OLStringUtil.getRandomAlpha(5));
		tEmailLogDataService.update(tEmailLog2);
		
		TEmailLog tEmailLog3 = tEmailLogDataService.get(tEmailLog1.getId());
		
		assertThat(testEmailLogUtil.compareEmailLog(tEmailLog2, tEmailLog3)).isTrue();
		assertThat(testEmailLogUtil.compareEmailLog(tEmailLog3, tEmailLog1)).isFalse();

		tEmailLogDataService.delete(tEmailLog3);
		
	}
	
}
