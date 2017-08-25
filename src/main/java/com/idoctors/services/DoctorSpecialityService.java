/**
 * @author Shukri Shukriev
**/
package com.idoctors.services;

import java.util.List;

import com.idoctors.domain.DoctorSpeciality;

public interface DoctorSpecialityService {
	
	DoctorSpeciality findDoctorSpecialityByDoctorId(Integer doctorId);
	
	List<DoctorSpeciality> findAllDoctorSpecialityByDoctorId(Integer doctorId);
	
	void deleteDoctorSpecialityById(Integer id);
	
	DoctorSpeciality saveDoctorSpeciality(DoctorSpeciality doctorSpeciality);
}
