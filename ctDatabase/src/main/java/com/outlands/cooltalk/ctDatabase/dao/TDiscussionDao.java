package com.outlands.cooltalk.ctDatabase.dao;

import org.springframework.stereotype.Repository;

import com.outlands.cooltalk.ctEntities.entity.TDiscussion;

@Repository
public class TDiscussionDao extends BaseDao<TDiscussion> {

	protected TDiscussionDao() {
		super(TDiscussion.class);
	}

}
