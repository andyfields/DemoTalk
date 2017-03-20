package com.outlands.cooltalk.microservices.repo;

import org.springframework.data.repository.CrudRepository;

import com.outlands.cooltalk.ctEntities.entity.TUser;

public interface TUserRepo extends CrudRepository<TUser, Long> {

}
