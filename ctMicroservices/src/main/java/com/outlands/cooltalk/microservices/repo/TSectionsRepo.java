package com.outlands.cooltalk.microservices.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.outlands.cooltalk.ctEntities.entity.TMessageSection;

public interface TSectionsRepo extends CrudRepository<TMessageSection, Long> {

    /**
     * Lookup a tour package by the name.
     *
     * @param name name of the tour.
     * @return TourPackage if found, null otherwise.
     */
	TMessageSection findByName(@Param("name") String name);

}