package com.outlands.cooltalk.ctCommon.properties;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testCommonContext.xml"})
public class SmtpPropertiesServiceITest  {
	
	@Autowired
	private SmtpPropertiesService smtpPropertiesService;
	
	/**
	 * Routine to test that properties are valid.
	 */
	@Test
	public void smtpPropertiesTest() {
		
		SmtpPropertiesServiceImpl svc = (SmtpPropertiesServiceImpl)smtpPropertiesService;
		
		Properties props = svc.getProperties();
		assertThat(props).isNotNull();
		
		assertThat(smtpPropertiesService.getConfirmReplyAddress()).isNotNull();
		assertThat(smtpPropertiesService.getSmtpServer()).isNotNull();
		assertThat(smtpPropertiesService.getSmtpUserLogon()).isNotNull();
		assertThat(smtpPropertiesService.getSmtpUserPassword()).isNotNull();
		assertThat(smtpPropertiesService.getSmtpPort()).isNotEqualTo(0);
	}
	
}
