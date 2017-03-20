package com.outlands.cooltalk.ctDatabase.dao;

import org.springframework.stereotype.Repository;

import com.outlands.cooltalk.ctEntities.entity.TLastMessageRead;

@Repository
public class TLastMessageReadDao extends BaseDao<TLastMessageRead> {

	protected TLastMessageReadDao() {
		super(TLastMessageRead.class);
	}

}
