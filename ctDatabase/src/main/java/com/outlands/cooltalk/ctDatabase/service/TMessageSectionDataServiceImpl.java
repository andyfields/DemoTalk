package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctDatabase.dao.BaseDao;
import com.outlands.cooltalk.ctDatabase.dao.TMessageSectionDao;
import com.outlands.cooltalk.ctEntities.entity.TMessageSection;

@Service
public class TMessageSectionDataServiceImpl extends BaseDataServiceImpl<TMessageSection> implements TMessageSectionDataService {
	
	@Autowired
	private TMessageSectionDao tMessageSectionDao;

	@Override
	protected BaseDao<TMessageSection> getDao() {
		return tMessageSectionDao;
	}

}
