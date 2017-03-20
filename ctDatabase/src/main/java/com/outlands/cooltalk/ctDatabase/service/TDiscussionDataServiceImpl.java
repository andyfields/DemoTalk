package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctDatabase.dao.BaseDao;
import com.outlands.cooltalk.ctDatabase.dao.TDiscussionDao;
import com.outlands.cooltalk.ctEntities.entity.TDiscussion;

@Service
public class TDiscussionDataServiceImpl extends BaseDataServiceImpl<TDiscussion> implements TDiscussionDataService {
	
	@Autowired
	private TDiscussionDao tDiscussionDao;

	@Override
	protected BaseDao<TDiscussion> getDao() {
		return tDiscussionDao;
	}

}
