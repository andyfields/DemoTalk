package com.outlands.cooltalk.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.outlands.cooltalk.ctEntities.entity.TMessageSection;
import com.outlands.cooltalk.microservices.repo.TSectionsRepo;

@Service
public class SectionService {
	
	@Autowired
	TSectionsRepo tSectionsRepo;
	public Iterable<TMessageSection> getAll() {
		
		return tSectionsRepo.findAll();
		
	}
	
	public void save(TMessageSection messageSection) {
		tSectionsRepo.save(messageSection);
	}
	
	public TMessageSection findByName(String name) {
		return tSectionsRepo.findByName(name);
	}
	
	public TMessageSection findOne(long id) {
		return tSectionsRepo.findOne(id);
	}
	
}
