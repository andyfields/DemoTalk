package com.outlands.cooltalk.web.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctCommon.constants.OLConstants;
import com.outlands.cooltalk.ctCommon.constants.SitePermission;
import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctDatabase.service.TUserDataService;
import com.outlands.cooltalk.ctEntities.entity.TDisplayName;
import com.outlands.cooltalk.ctEntities.entity.TUser;
import com.outlands.cooltalk.ctEntities.entity.TUser.UserStatus;
import com.outlands.cooltalk.utility.OLDateUtils;
import com.outlands.cooltalk.web.security.HashUtility;

@Service
public class UserService {
	
	@Autowired 
	private OLDateUtils olDateUtils;
	
	@Autowired
	private TUserDataService tUserDataService;
	
	@Autowired
	private HashUtility hashUtility;
	
	/**
	 * Create user with info necessary for registration.
	 * 
	 * @param eMail
	 * @param password
	 * @param displayName
	 * @return
	 * @throws DaoException 
	 */
	public TUser createNewUser(String eMail, String password, String displayName, UserStatus userStatus, String confirmCode) throws DaoException {
		TUser user = new TUser();
		user.setPrimaryEMail(eMail);
		user.setDisplayName(displayName);
		
		TDisplayName dispName = new TDisplayName(user, displayName, new Date());
		user.getDisplayNames().add(dispName);
		
		// Get salt and hash for password.
		byte[] pwdSalt = hashUtility.getRandomBuf(20);
		user.setPasswordSalt(pwdSalt);
		
		byte[] pwdHash = hashUtility.hash(pwdSalt, password);
		user.setPasswordHash(pwdHash);
		
		user.setConfirmationCode(confirmCode);
		
		user.setConfirmationSent(new Date());
		
		user.setUserStatus(userStatus);
		
		user.setPermission(SitePermission.ROLE_USER);
		
		tUserDataService.save(user);
		
		return user;
	}
	
	public TUser updateUserPassword(TUser user, String password) throws DaoException {
		
		// Get salt and hash for password.
		byte[] pwdSalt = hashUtility.getRandomBuf(20);
		user.setPasswordSalt(pwdSalt);
		
		byte[] pwdHash = hashUtility.hash(pwdSalt, password);
		user.setPasswordHash(pwdHash);
		
		user.setChangePasswordCode(null);
		user.setChangePasswordSent(null);
		
		tUserDataService.update(user);
		
		return user;
	}
	
	public boolean isUserConfirmExpired(TUser user) {
		return (user.getUserStatus().equals(UserStatus.CONFIRMATION_SENT)  || user.getUserStatus().equals(UserStatus.CONFIRMATION_RESENT)) && 
				olDateUtils.isExpired(user.getConfirmationSent(), Calendar.MINUTE, OLConstants.CONFIRMATION_EXPIRATION_MINS);
	}

	public boolean isChangePasswordExpired(TUser user) {
		return olDateUtils.isExpired(user.getChangePasswordSent(), Calendar.MINUTE, OLConstants.RESET_PASSWORD_EXPIRATION_MINS);
	}

	public Date getSuspensionEndTime(TUser user) {
		Date startSuspended = user.getSuspensionStart();
		if (startSuspended == null) return null;
		
		return olDateUtils.add(startSuspended, Calendar.DATE, (int)user.getSuspensionLength());		
	}
	
	
	public boolean isSuspended(TUser user) {
		
		Date startSuspended = user.getSuspensionStart();
		if (startSuspended == null) return false;
		
		Date endSuspended = getSuspensionEndTime(user);
		
		return olDateUtils.inRange(olDateUtils.getNow(), startSuspended, endSuspended);
	}
	
}
