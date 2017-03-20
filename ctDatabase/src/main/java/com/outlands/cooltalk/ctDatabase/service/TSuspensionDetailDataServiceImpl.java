package com.outlands.cooltalk.ctDatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctDatabase.dao.BaseDao;
import com.outlands.cooltalk.ctDatabase.dao.TSuspensionDetailDao;
import com.outlands.cooltalk.ctEntities.entity.TSuspensionDetail;

@Service
public class TSuspensionDetailDataServiceImpl extends BaseDataServiceImpl<TSuspensionDetail> implements TSuspensionDetailDataService {
	
	@Autowired
	private TSuspensionDetailDao tSuspensionDetailDao;

	@Override
	protected BaseDao<TSuspensionDetail> getDao() {
		return tSuspensionDetailDao;
	}

}
