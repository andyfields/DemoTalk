package com.outlands.cooltalk.ctDatabase.dao;

import org.springframework.stereotype.Repository;

import com.outlands.cooltalk.ctEntities.entity.TSuspensionDetail;

@Repository
public class TSuspensionDetailDao extends BaseDao<TSuspensionDetail> {

	protected TSuspensionDetailDao() {
		super(TSuspensionDetail.class);
	}

}
