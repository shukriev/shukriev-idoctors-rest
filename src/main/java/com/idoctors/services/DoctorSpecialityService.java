package com.idoctors.services;

import com.idoctors.domain.DoctorEducation;

public interface DoctorEducationService {
	
	Iterable<DoctorEducation> listAllniversities();

	DoctorEducation getDoctorEducationByDoctorId(Integer id);

	DoctorEducation saveDoctor(DoctorEducation doctorEducation);
}
