package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctDatabase.dao.BaseDao;
import com.outlands.cooltalk.ctDatabase.dao.TDisplayNameDao;
import com.outlands.cooltalk.ctEntities.entity.TDisplayName;

@Service
public class TDisplayNameDataServiceImpl extends BaseDataServiceImpl<TDisplayName> implements TDisplayNameDataService {
	
	@Autowired
	private TDisplayNameDao tDisplayNameDao;

	@Override
	protected BaseDao<TDisplayName> getDao() {
		return tDisplayNameDao;
	}

}
