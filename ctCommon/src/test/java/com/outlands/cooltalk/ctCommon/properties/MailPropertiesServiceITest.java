package com.outlands.cooltalk.ctCommon.properties;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testCommonContext.xml"})
public class MailPropertiesServiceITest  {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private MailPropertiesService mailPropertiesService;
	
	/**
	 * Routine to test that properties are valid.
	 */
	@Test
	public void mailPropertiesTest() {
		
		MailPropertiesServiceImpl svc = (MailPropertiesServiceImpl)mailPropertiesService;
		
		Properties props = svc.getProperties();
		assertThat(props).isNotNull();
		
		System.out.println(mailPropertiesService.getReplyTo());
		assertThat(mailPropertiesService.getFrom()).isNotNull();
		System.out.println(mailPropertiesService.getFrom());
		assertThat(mailPropertiesService.getMailServer()).isNotNull();
		System.out.println(mailPropertiesService.getReplyTo());
		assertThat(mailPropertiesService.getReplyTo()).isNotNull();
		assertThat(mailPropertiesService.getServerId()).isNotNull();
		assertThat(mailPropertiesService.getServerPassword()).isNotNull();
	}
	
}
