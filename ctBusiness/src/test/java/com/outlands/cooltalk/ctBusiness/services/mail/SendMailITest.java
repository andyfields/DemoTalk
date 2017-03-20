package com.outlands.cooltalk.ctBusiness.services.mail;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.outlands.cooltalk.ctBusiness.services.mail.SendMailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testBusinessContext.xml"})
public class SendMailITest {
	
	@Autowired
	private SendMailService sendMailService; 

	// Warning!! This will send an email!!
	@Test
	//@Ignore
	public void sendMailTest() throws ClientProtocolException, IOException  {
		
		//int rs = sendMailService.sendConfirmation("Andy Fields <andyfields@asfields.com>", "Confirmation test message", "This is a confirmation test message");
		int rs = sendMailService.send("fieldsasf@gmail.com", "Confirmation test message", "This is a confirmation test message");
		System.out.println(rs);
		
	}
}
