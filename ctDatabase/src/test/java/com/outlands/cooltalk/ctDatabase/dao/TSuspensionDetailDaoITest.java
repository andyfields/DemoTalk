package com.outlands.cooltalk.ctDatabase.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctDatabase.service.TSuspensionDetailDataService;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctDatabase.testUtil.TestSuspensionDetailUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestUserUtil;
import com.outlands.cooltalk.ctEntities.entity.TSuspensionDetail;
import com.outlands.cooltalk.ctEntities.entity.TUser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:hibernateTestContext.xml"})
public class TSuspensionDetailDaoITest {

	@Autowired
	private TSuspensionDetailDataService tSuspensionDetailDataService;
	
	@Autowired
	private TUserDataService tUserDataService;
	
	@Autowired
	private TestSuspensionDetailUtil testSuspensionDetailUtil;
	
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Test
	public void testTSuspensionDetail_create1SuspensionDetail_success() {
		TUser user = testUserUtil.createTestUser("1");
		tUserDataService.save(user);
		
		TSuspensionDetail tSuspensionDetail1 = testSuspensionDetailUtil.createTestSuspensionDetail(user, "2");
		tSuspensionDetailDataService.save(tSuspensionDetail1);
		
		TSuspensionDetail tSuspensionDetail2 = tSuspensionDetailDataService.get(tSuspensionDetail1.getId());
		
		assertThat(testSuspensionDetailUtil.compareSuspensionDetails(tSuspensionDetail1, tSuspensionDetail2)).isTrue();
		
		tSuspensionDetailDataService.delete(tSuspensionDetail1);
		tUserDataService.delete(user);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testTSuspensionDetail_update1SuspensionDetail_success() {
		TUser user = testUserUtil.createTestUser("1");
		tUserDataService.save(user);
		
		TSuspensionDetail tSuspensionDetail1 = testSuspensionDetailUtil.createTestSuspensionDetail(user, "1");
		tSuspensionDetailDataService.save(tSuspensionDetail1);

		TSuspensionDetail tSuspensionDetail2 = tSuspensionDetailDataService.get(tSuspensionDetail1.getId());
		
		assertThat(testSuspensionDetailUtil.compareSuspensionDetails(tSuspensionDetail1, tSuspensionDetail2)).isTrue();
		
		tSuspensionDetail2.setReason("New Reason");
		tSuspensionDetailDataService.update(tSuspensionDetail2);
		
		TSuspensionDetail tSuspensionDetail3 = tSuspensionDetailDataService.get(tSuspensionDetail1.getId());
		
		assertThat(testSuspensionDetailUtil.compareSuspensionDetails(tSuspensionDetail2, tSuspensionDetail3)).isTrue();
		assertThat(testSuspensionDetailUtil.compareSuspensionDetails(tSuspensionDetail3, tSuspensionDetail1)).isFalse();

		tSuspensionDetailDataService.delete(tSuspensionDetail3);
		tUserDataService.delete(user);
		
		System.out.println("Success!");
	}
	
	
}
