package com.outlands.cooltalk.ctDatabase.service;

import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctEntities.entity.TUser;

public interface TUserDataService extends BaseDataService<TUser> {

	public TUser getUserByPrimaryEMail(String primaryEMail) throws DaoException;

	public TUser getUserByConfCode(String confCode) throws DaoException;

	public TUser getUserByChangeEmailCode(String changeEMailCode) throws DaoException;

	public TUser getUserByDisplayName(String primaryEMail) throws DaoException;

	public TUser getUserByChangePasswordCode(String changePasswordCode) throws DaoException;

}
