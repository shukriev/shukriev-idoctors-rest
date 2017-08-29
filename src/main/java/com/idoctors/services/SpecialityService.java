/**
 * @author Shukri Shukriev
 *
 */
package com.idoctors.services;

import com.idoctors.domain.Speciality;

public interface SpecialityService {
	Iterable<Speciality> findAllSpecialities();

	Speciality getSpecialityById(Integer id);

	Speciality saveSpeciality(Speciality speciality);
}
