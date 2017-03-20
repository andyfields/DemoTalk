package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctDatabase.dao.BaseDao;
import com.outlands.cooltalk.ctDatabase.dao.TMessageDao;
import com.outlands.cooltalk.ctEntities.entity.TMessage;

@Service
public class TMessageDataServiceImpl extends BaseDataServiceImpl<TMessage> implements TMessageDataService {
	
	@Autowired
	private TMessageDao tMessageDao;

	@Override
	protected BaseDao<TMessage> getDao() {
		return tMessageDao;
	}

}
