/**
 * @author Shukri Shukriev
**/
package com.idoctors.service.impl;

import org.springframework.stereotype.Service;

import com.idoctors.domain.University;
import com.idoctors.repositories.UniversityRepository;
import com.idoctors.services.UniversityService;

@Service
public class UniversityServiceImpl implements UniversityService {
	private UniversityRepository repository;

	@Override
	public Iterable<University> findAllUniversities() {
		return repository.findAll();
	}

	@Override
	public University getUniversityById(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public University saveUniversity(University university) {
		return repository.save(university);
	}
}
