package com.idoctors.services;

import com.idoctors.domain.University;

public interface UniversityService {
	
	Iterable<University> listAllUniversities();

	University getUniversityById(Integer id);

	University saveUniversity(University university);
}
