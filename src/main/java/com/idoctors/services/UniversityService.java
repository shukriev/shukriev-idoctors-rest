/**
 * @author Shukri Shukriev
**/
package com.idoctors.services;

import com.idoctors.domain.University;

public interface UniversityService {
	
	Iterable<University> findAllUniversities();

	University getUniversityById(Integer id);

	University saveUniversity(University university);
}
