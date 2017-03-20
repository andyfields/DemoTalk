package com.outlands.cooltalk.ctDatabase.testUtil;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.utility.OLHashUtility;
import com.outlands.cooltalk.utility.OLObjectUtils;
import com.outlands.cooltalk.utility.OLStringUtil;

@Service
public class TestUserUtil {
	
	@Autowired
	private OLStringUtil olStringUtils;

	@Autowired
	private OLObjectUtils olObjectUtils;

	@Autowired
	private OLHashUtility olHashUtility;

	public TUser createTestUser(String suffix) {
		String randChars = OLStringUtil.getRandomAlpha(5);
		TUser user = new TUser();
		user.setPrimaryEMail("user" + randChars + suffix + "@email.com");
		byte[] salt = olHashUtility.getRandomBuf(20);
		user.setPasswordSalt(salt);
		byte[] pwHash = olHashUtility.hash(salt, "Password" + suffix);
		user.setPasswordHash(pwHash);
		user.setDisplayName("DisplayName" + randChars + suffix);
		user.setTagline("Tagline" + suffix);
		user.setUserStatus(TUser.UserStatus.CONFIRMATION_SENT);
		
		String confCode = OLStringUtil.getRandomAlpha(20);
		user.setConfirmationCode(confCode);
		
		String changeEmailCode = OLStringUtil.getRandomAlpha(20);
		user.setChangeEmailCode(changeEmailCode);
		
		String changePasswordCode = OLStringUtil.getRandomAlpha(20);
		user.setChangePasswordCode(changePasswordCode);
		
		return user;
	}

	public  boolean compareUsers(TUser user1, TUser user2) {
		
		if (!olStringUtils.isEqual(user1.getPrimaryEMail(), user2.getPrimaryEMail())) return false;
		
		if (!Arrays.equals(user1.getPasswordSalt(), user2.getPasswordSalt())) return false;
		
		if (!Arrays.equals(user1.getPasswordHash(), user2.getPasswordHash())) return false;
		
		if (!olStringUtils.isEqual(user1.getDisplayName(), user2.getDisplayName())) return false;
		
		if (!olStringUtils.isEqual(user1.getConfirmationCode(), user2.getConfirmationCode())) return false;
		
		if (!olStringUtils.isEqual(user1.getChangeEmailCode(), user2.getChangeEmailCode())) return false;
		
		if (!olStringUtils.isEqual(user1.getChangePasswordCode(), user2.getChangePasswordCode())) return false;
		
		if (!olStringUtils.isEqual(user1.getTagline(), user2.getTagline())) return false;
		
		if (!olObjectUtils.isEqual(user1.getUserStatus(), user2.getUserStatus())) return false;
		
		return true;
		
	}
}
