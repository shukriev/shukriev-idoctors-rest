package com.idoctors.services;

import com.idoctors.domain.Doctor;

public interface DoctorService {
	
	Iterable<Doctor> listAllDoctors();

	Doctor getDoctorById(Integer id);

	Integer saveDoctor(Doctor doctor);
	
	void deleteDoctorById(Integer id);
	
	Doctor updateDoctor(Doctor doctor);
}
