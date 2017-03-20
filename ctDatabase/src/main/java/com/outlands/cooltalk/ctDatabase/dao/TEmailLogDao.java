package com.outlands.cooltalk.ctDatabase.dao;

import org.springframework.stereotype.Repository;

import com.outlands.cooltalk.ctEntities.entity.TEmailLog;

@Repository
public class TEmailLogDao extends BaseDao<TEmailLog> {

	protected TEmailLogDao() {
		super(TEmailLog.class);
	}

}
