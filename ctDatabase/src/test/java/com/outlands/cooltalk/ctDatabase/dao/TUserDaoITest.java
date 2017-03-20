package com.outlands.cooltalk.ctDatabase.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctDatabase.testUtil.TestUserUtil;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLStringUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:hibernateTestContext.xml"})
public class TUserDaoITest {

	@Autowired
	private TUserDataService tUserDataService;
	
	@Autowired
	private TestUserUtil testUserUtil;
	
	@Test
	public void testTuser_create1User_success() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.get(tUser1.getId());
		
		assertThat(testUserUtil.compareUsers(tUser1, tUser2)).isTrue();
		
		tUserDataService.delete(tUser1);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testTuser_update1User_success() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.get(tUser1.getId());
		
		assertThat(testUserUtil.compareUsers(tUser1, tUser2)).isTrue();
		
		tUser2.setDisplayName("New display name " + OLStringUtil.getRandomAlpha(5));
		tUserDataService.update(tUser2);
		
		TUser tUser3 = tUserDataService.get(tUser1.getId());
		
		assertThat(testUserUtil.compareUsers(tUser2, tUser3)).isTrue();
		assertThat(testUserUtil.compareUsers(tUser3, tUser1)).isFalse();

		tUserDataService.delete(tUser3);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserByEmail_success() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByPrimaryEMail(tUser1.getPrimaryEMail());
		
		assertThat(testUserUtil.compareUsers(tUser1, tUser2)).isTrue();
		
		tUserDataService.delete(tUser2);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserByEmail_noResult() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByPrimaryEMail("xxx");
		
		assertThat(tUser2).isNull();
		
		tUserDataService.delete(tUser1);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserByDisplayName_success() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByDisplayName(tUser1.getDisplayName());
		
		assertThat(testUserUtil.compareUsers(tUser1, tUser2)).isTrue();
		
		tUserDataService.delete(tUser2);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserByDisplayName_noResult() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByDisplayName("xxx");
		
		assertThat(tUser2).isNull();
		
		tUserDataService.delete(tUser1);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserConfCode_success() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByConfCode(tUser1.getConfirmationCode());
		
		assertThat(testUserUtil.compareUsers(tUser1, tUser2)).isTrue();
		
		tUserDataService.delete(tUser2);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserConfCode_noResult() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByConfCode("xxx");
		
		assertThat(tUser2).isNull();
		
		tUserDataService.delete(tUser1);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserByChangeEmailCode_success() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByChangeEmailCode(tUser1.getChangeEmailCode());
		
		assertThat(testUserUtil.compareUsers(tUser1, tUser2)).isTrue();
		
		tUserDataService.delete(tUser2);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserByChangeEmailCode_noResult() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByChangeEmailCode("xxx");
		
		assertThat(tUser2).isNull();
		
		tUserDataService.delete(tUser1);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserByChangePassowrdCode_success() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByChangePasswordCode(tUser1.getChangePasswordCode());
		
		assertThat(testUserUtil.compareUsers(tUser1, tUser2)).isTrue();
		
		tUserDataService.delete(tUser2);
		
		System.out.println("Success!");
	}
	
	@Test
	public void testGetUserByChangePasswordCode_noResult() {
		TUser tUser1 = testUserUtil.createTestUser("1");
		
		tUserDataService.save(tUser1);
		TUser tUser2 = tUserDataService.getUserByChangePasswordCode("xxx");
		
		assertThat(tUser2).isNull();
		
		tUserDataService.delete(tUser1);
		
		System.out.println("Success!");
	}
		
}
