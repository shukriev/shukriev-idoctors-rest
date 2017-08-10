/**
 * @author Shukri Shukriev
 *
 */
package com.idoctors.services;

import com.idoctors.domain.Speciality;

public interface SpecialityService {
	Iterable<Speciality> listAllSpecialities();

	Speciality getSpecialityById(Integer id);

	Speciality saveSpeciality(Speciality speciality);
}
