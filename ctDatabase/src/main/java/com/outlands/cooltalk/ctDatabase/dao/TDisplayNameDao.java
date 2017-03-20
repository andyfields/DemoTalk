package com.outlands.cooltalk.ctDatabase.dao;

import org.springframework.stereotype.Repository;

import com.outlands.cooltalk.ctEntities.entity.TDisplayName;

@Repository
public class TDisplayNameDao extends BaseDao<TDisplayName> {

	protected TDisplayNameDao() {
		super(TDisplayName.class);
	}

}
