package com.outlands.cooltalk.ctDatabase.dao;

import org.springframework.stereotype.Repository;

import com.outlands.cooltalk.ctEntities.entity.TMessage;

@Repository
public class TMessageDao extends BaseDao<TMessage> {

	protected TMessageDao() {
		super(TMessage.class);
	}

}
