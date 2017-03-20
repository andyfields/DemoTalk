package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctDatabase.dao.BaseDao;
import com.outlands.cooltalk.ctDatabase.dao.TUserDao;
import com.outlands.cooltalk.ctEntities.entity.TUser;

@Service
public class TUserDataServiceImpl extends BaseDataServiceImpl<TUser> implements TUserDataService {
	
	@Autowired
	private TUserDao tUserDao;

	@Override
	protected BaseDao<TUser> getDao() {
		return tUserDao;
	}

	@Override
	@Transactional(readOnly=true)
	public TUser getUserByPrimaryEMail(String primaryEMail) throws DaoException {
		return tUserDao.getUserByPrimaryEMail(primaryEMail);
	}

	@Override
	@Transactional(readOnly=true)
	public TUser getUserByConfCode(String confCode) throws DaoException {
		return tUserDao.getUserByConfCode(confCode);
	}

	@Override
	@Transactional(readOnly=true)
	public TUser getUserByDisplayName(String displayName) throws DaoException {
		return tUserDao.getUserByDisplayName(displayName);
	}

	@Override
	@Transactional(readOnly=true)
	public TUser getUserByChangeEmailCode(String changeEMailCode) throws DaoException {
		return tUserDao.getUserByChangeEmailCode(changeEMailCode);
	}
	
	@Override
	@Transactional(readOnly=true)
	public TUser getUserByChangePasswordCode(String changePasswordCode) throws DaoException {
		return tUserDao.getUserByChangePasswordCode(changePasswordCode);
	}
	
}
