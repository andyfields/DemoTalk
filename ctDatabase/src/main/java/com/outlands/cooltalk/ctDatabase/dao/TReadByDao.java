package com.outlands.cooltalk.ctDatabase.dao;

import org.springframework.stereotype.Repository;

import com.outlands.cooltalk.ctEntities.entity.TReadBy;

@Repository
public class TReadByDao extends BaseDao<TReadBy> {

	protected TReadByDao() {
		super(TReadBy.class);
	}

}
