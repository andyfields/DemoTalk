package com.outlands.cooltalk.ctCommon.properties;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctCommon.properties.OLWebPropertiesService;
import com.outlands.cooltalk.ctCommon.properties.OLWebPropertiesServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testWebContext.xml"})
public class WebPropertiesServiceITest  {
	
	@Autowired
	@Qualifier("webProperties")
	private OLWebPropertiesService olWebPropertiesService;
	
	/**
	 * Routine to test that resource message IDs are valid.
	 */
	@Test
	public void webPropertiesTest() {
		
		OLWebPropertiesServiceImpl svc = (OLWebPropertiesServiceImpl)olWebPropertiesService;
		
		Properties props = svc.getProperties();
		assertThat(props).isNotNull();
		
		assertThat(props.get("DemoMode")).isNotNull();
		assertThat(svc.getMicroservicesServer()).isNotNull();

	}
	
}
