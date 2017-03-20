package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctDatabase.dao.BaseDao;
import com.outlands.cooltalk.ctDatabase.dao.TLastMessageReadDao;
import com.outlands.cooltalk.ctEntities.entity.TLastMessageRead;

@Service
public class TLastMessageReadDataServiceImpl extends BaseDataServiceImpl<TLastMessageRead> implements TLastMessageReadDataService {
	
	@Autowired
	private TLastMessageReadDao tLastMessageReadDao;

	@Override
	protected BaseDao<TLastMessageRead> getDao() {
		return tLastMessageReadDao;
	}

}
