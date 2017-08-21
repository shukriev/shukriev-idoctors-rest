/**
 * @author Shukri Shukriev
**/
package com.idoctors.services;

import java.util.List;
import java.util.Set;

import com.idoctors.domain.DoctorSpeciality;

public interface DoctorSpecialityService {
	
	DoctorSpeciality getDoctorEducationByDoctorId(Integer doctorId);
	
	List<DoctorSpeciality> findAllDoctorEducationByDoctorId(Integer doctorId);
	
	void deleteDoctorEducationById(Integer id);
	
	DoctorSpeciality saveDoctorEducation(DoctorSpeciality doctorEducation);
}
