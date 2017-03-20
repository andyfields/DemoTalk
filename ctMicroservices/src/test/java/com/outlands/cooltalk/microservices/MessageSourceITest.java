package com.outlands.cooltalk.microservices;

import static org.junit.Assert.fail;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.outlands.cooltalk.ctCommon.constants.OLDialogMessageConstants;
import com.outlands.cooltalk.ctCommon.constants.OLEmailMessageConstants;
import com.outlands.cooltalk.ctCommon.constants.OLSingleMessageConstants;
import com.outlands.cooltalk.ctCommon.services.MessageService;
import com.outlands.cooltalk.ctCommon.services.MessageService.EMailMessageBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ctCommonContext.xml", "classpath:/outlandsUtilityContext.xml"})
@SuppressWarnings("deprecation")
public class MessageSourceITest  {
	
	@Autowired
	@Qualifier("messageSource")
	MessageSource messageSource;
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * Routine to test that resource message IDs are valid.
	 */
	@Test
	public void testBadMessageId() {
		
		Locale locale = Locale.US;
				
		// Test for bad message ID.
		try {
			Assert.isNull(messageSource.getMessage("..", null, locale));
			fail("Expected exception.");
		} catch (Exception e) {
			System.out.println("Passed - Expected exception thrown");
		}
	}
		
	@Test
	public void testDialogMessages() {
		
		Locale locale = Locale.US;
		
		String[] args = new String[] {"arg0", "arg1", "arg2"};
		
		for (OLDialogMessageConstants dialogConst : OLDialogMessageConstants.values()){
			testDialogMessage(dialogConst, args, locale);
		}
				
//		testDialogTitleDescMessage(MessageConstants.ADD_DISCUSSION_MSG, new String[]{"Section name arg"}, locale);
	}

	@Test
	public void testSingleMessages() {
		
		Locale locale = Locale.US;
		
		String[] args = new String[] {"arg0", "arg1", "arg2"};
		
		for (OLSingleMessageConstants dialogConst : OLSingleMessageConstants.values()){
			Assert.notNull(messageSource.getMessage(dialogConst.getMessageKey(), args, locale));
		}
				
//		testDialogTitleDescMessage(MessageConstants.ADD_DISCUSSION_MSG, new String[]{"Section name arg"}, locale);
	}

		
	@Test
	public void testEmailMessages() {
		
		Locale locale = Locale.US;
		
		String[] args = new String[] {"arg0", "arg1", "arg2"};
		
		for (OLEmailMessageConstants dialogConst : OLEmailMessageConstants.values()){
			testEmail(dialogConst, args,locale);
		}
				
	}
	
	@SuppressWarnings("unused")
	private void testDialogTitleDescMessage(OLDialogMessageConstants dialogConst, String[] args, Locale locale) {
		Assert.notNull(messageSource.getMessage(dialogConst.getMessageKey() + ".titleLabel", args, locale));
		
		testDialogMessage(dialogConst, args, locale);
	}
		
	private void testDialogMessage(OLDialogMessageConstants dialogConst, String[] args, Locale locale) {
		
		Assert.notNull(messageSource.getMessage(dialogConst.getMessageKey() + ".title", args, locale));
		Assert.notNull(messageSource.getMessage(dialogConst.getMessageKey() + ".banner", args, locale));
		String sCount = messageSource.getMessage(dialogConst.getMessageKey() + ".count", args, locale);
		Assert.notNull(sCount);
		int nCount = Integer.parseInt(sCount);
		
		for (int i = 0; i < nCount; i++) {
			Assert.notNull("Message Key: " + dialogConst.name() , messageSource.getMessage(dialogConst.getMessageKey() + ".message" + i, args, locale));
		}
	}
	
	
	private boolean testEmail(OLEmailMessageConstants emailConst, String[] args, Locale locale) {
		
		try {
			EMailMessageBean bean = messageService.createEmailMesssageBean(emailConst, args, locale);
			assertThat(bean).isNotNull();
			assertThat(bean.getSubject()).isNotEmpty();
			assertThat(bean.getMessage()).isNotEmpty();
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

}
