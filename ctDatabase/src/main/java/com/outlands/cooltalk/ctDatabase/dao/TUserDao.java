package com.outlands.cooltalk.ctDatabase.dao;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.outlands.cooltalk.ctCommon.exceptions.DaoException;
import com.outlands.cooltalk.ctEntities.entity.TUser;

@Repository
public class TUserDao extends BaseDao<TUser> {

	protected TUserDao() {
		super(TUser.class);
	}
	
	public TUser getUserByPrimaryEMail(String primaryEMail) throws DaoException {
		try {
			@SuppressWarnings("rawtypes")
			Query q = getCurrentSession().createQuery("from TUser where primaryEMail = :primaryEMail");
			q.setParameter("primaryEMail", primaryEMail);
			
			TUser user = (TUser)q.getSingleResult();
			return user;
			
		} catch (NoResultException e) {
			return null;
		
		} catch (HibernateException e) {
			throw new DaoException("Could not get TUser w primaryEMail: " + primaryEMail, e);
		}
	}
	
	public TUser getUserByConfCode(String confirmationCode) throws DaoException {
		try {
			@SuppressWarnings("rawtypes")
			Query q = getCurrentSession().createQuery("from TUser where confirmationCode = :confirmationCode");
			q.setParameter("confirmationCode", confirmationCode);
			
			TUser user = (TUser)q.getSingleResult();
			return user;
			
		} catch (NoResultException e) {
			return null;
		
		} catch (HibernateException e) {
			throw new DaoException("Could not get TUser w confirmationCode: " + confirmationCode, e);
		}
	}
	
	public TUser getUserByChangeEmailCode(String changeEmailCode) throws DaoException {
		try {
			@SuppressWarnings("rawtypes")
			Query q = getCurrentSession().createQuery("from TUser where changeEmailCode = :changeEmailCode");
			q.setParameter("changeEmailCode", changeEmailCode);
			
			TUser user = (TUser)q.getSingleResult();
			return user;
			
		} catch (NoResultException e) {
			return null;
		
		} catch (HibernateException e) {
			throw new DaoException("Could not get TUser w changeEmailCode: " + changeEmailCode, e);
		}
	}
	
	public TUser getUserByChangePasswordCode(String changePasswordCode) throws DaoException {
		try {
			@SuppressWarnings("rawtypes")
			Query q = getCurrentSession().createQuery("from TUser where changePasswordCode = :changePasswordCode");
			q.setParameter("changePasswordCode", changePasswordCode);
			
			TUser user = (TUser)q.getSingleResult();
			return user;
			
		} catch (NoResultException e) {
			return null;
		
		} catch (HibernateException e) {
			throw new DaoException("Could not get TUser w changePasswordCode: " + changePasswordCode, e);
		}
	}
	
	public TUser getUserByDisplayName(String displayName) throws DaoException {
		try {
			@SuppressWarnings("rawtypes")
			Query q = getCurrentSession().createQuery("from TUser where displayName = :displayName");
			q.setParameter("displayName", displayName);
			
			TUser user = (TUser)q.getSingleResult();
			return user;
			
		} catch (NoResultException e) {
			return null;
		
		} catch (HibernateException e) {
			throw new DaoException("Could not get TUser w displayName: " + displayName, e);
		}
	}
	

}
