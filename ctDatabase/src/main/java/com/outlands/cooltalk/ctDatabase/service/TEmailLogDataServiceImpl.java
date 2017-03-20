package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctDatabase.dao.BaseDao;
import com.outlands.cooltalk.ctDatabase.dao.TEmailLogDao;
import com.outlands.cooltalk.ctEntities.entity.TEmailLog;

@Service
public class TEmailLogDataServiceImpl extends BaseDataServiceImpl<TEmailLog> implements TEmailLogDataService {
	
	@Autowired
	private TEmailLogDao tEmailLogDao;

	@Override
	protected BaseDao<TEmailLog> getDao() {
		return tEmailLogDao;
	}

}
