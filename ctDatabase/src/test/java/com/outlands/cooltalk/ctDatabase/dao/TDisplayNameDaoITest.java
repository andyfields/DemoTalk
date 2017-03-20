package com.outlands.cooltalk.ctDatabase.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctDatabase.service.TDisplayNameDataService;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctDatabase.testUtil.TestDisplayNameUtil;
import com.outlands.cooltalk.ctDatabase.testUtil.TestUserUtil;
import com.outlands.cooltalk.ctEntities.entity.TDisplayName;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLStringUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:hibernateTestContext.xml"})
public class TDisplayNameDaoITest {

	@Autowired
	private TDisplayNameDataService tDisplayNameDataService;
	
	@Autowired
	private TUserDataService tUserDataService;
	
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Autowired
	private TestDisplayNameUtil testDisplayNameUtil;
	
	@Test
	public void testTDisplayName_create1DisplayName_success() {
		TUser tUser = testUserUtil.createTestUser("1");
		tUserDataService.save(tUser);
		
		TDisplayName tDisplayName1 = testDisplayNameUtil.createDisplayName(tUser, "1");
		tDisplayNameDataService.save(tDisplayName1);
		
		TDisplayName tDisplayName2 = tDisplayNameDataService.get(tDisplayName1.getId());
		
		assertThat(testDisplayNameUtil.compareDisplayNames(tDisplayName1, tDisplayName2)).isTrue();
		
		tDisplayNameDataService.delete(tDisplayName1);
		tUserDataService.delete(tUser);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testTDisplayName_update1DisplayName_success() {
		TUser tUser = testUserUtil.createTestUser("1");
		tUserDataService.save(tUser);

		TDisplayName tDisplayName1 = testDisplayNameUtil.createDisplayName(tUser, "1");
		tDisplayNameDataService.save(tDisplayName1);
		
		TDisplayName tDisplayName2 = tDisplayNameDataService.get(tDisplayName1.getId());
		
		assertThat(testDisplayNameUtil.compareDisplayNames(tDisplayName1, tDisplayName2)).isTrue();
		
		tDisplayName2.setDisplayName("New Display Name " + OLStringUtil.getRandomAlpha(5));
		tDisplayNameDataService.update(tDisplayName2);
		
		TDisplayName tDisplayName3 = tDisplayNameDataService.get(tDisplayName1.getId());
		
		assertThat(testDisplayNameUtil.compareDisplayNames(tDisplayName2, tDisplayName3)).isTrue();
		assertThat(testDisplayNameUtil.compareDisplayNames(tDisplayName3, tDisplayName1)).isFalse();

		tDisplayNameDataService.delete(tDisplayName3);
		tUserDataService.delete(tUser);
		
		System.out.println("Success!");
	}
	
}
