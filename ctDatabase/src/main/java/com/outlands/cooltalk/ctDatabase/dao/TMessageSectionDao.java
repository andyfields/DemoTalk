package com.outlands.cooltalk.ctDatabase.dao;

import org.springframework.stereotype.Repository;

import com.outlands.cooltalk.ctEntities.entity.TMessageSection;

@Repository
public class TMessageSectionDao extends BaseDao<TMessageSection> {

	protected TMessageSectionDao() {
		super(TMessageSection.class);
	}

}
