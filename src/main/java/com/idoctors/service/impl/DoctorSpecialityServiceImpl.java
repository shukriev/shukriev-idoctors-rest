/**
 * @author Shukri Shukriev
**/
package com.idoctors.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idoctors.domain.DoctorSpeciality;
import com.idoctors.repositories.DoctorSpecialityRepository;
import com.idoctors.services.DoctorSpecialityService;

@Service
public class DoctorSpecialityServiceImpl implements DoctorSpecialityService {

	@Autowired
	private DoctorSpecialityRepository doctorEducationRepository;

	@Override
	public DoctorSpeciality getDoctorEducationByDoctorId(Integer doctorId) {
		return doctorEducationRepository.findOne(doctorId);
	}

	@Override
	public List<DoctorSpeciality> findAllDoctorEducationByDoctorId(Integer doctorId) {
		return doctorEducationRepository.findAllDoctorEducationByDoctorId(doctorId);
	}

	@Override
	public void deleteDoctorEducationById(Integer id) {
		doctorEducationRepository.delete(id);
	}

	@Override
	public DoctorSpeciality saveDoctorEducation(DoctorSpeciality doctorEducation) {
		return doctorEducationRepository.save(doctorEducation);
	}
}
