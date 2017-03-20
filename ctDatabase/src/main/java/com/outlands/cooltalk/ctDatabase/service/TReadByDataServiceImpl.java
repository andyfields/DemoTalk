package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctDatabase.dao.BaseDao;
import com.outlands.cooltalk.ctDatabase.dao.TReadByDao;
import com.outlands.cooltalk.ctEntities.entity.TReadBy;

@Service
public class TReadByDataServiceImpl extends BaseDataServiceImpl<TReadBy> implements TReadByDataService {
	
	@Autowired
	private TReadByDao tReadByDao;

	@Override
	protected BaseDao<TReadBy> getDao() {
		return tReadByDao;
	}

}
