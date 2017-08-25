/**
 * @author Shukri Shukriev
**/
package com.idoctors.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idoctors.domain.DoctorSpeciality;
import com.idoctors.repositories.DoctorSpecialityRepository;
import com.idoctors.services.DoctorSpecialityService;

@Service
public class DoctorSpecialityServiceImpl implements DoctorSpecialityService {

	@Autowired
	private DoctorSpecialityRepository doctorSpecialityRepository;

	@Override
	public DoctorSpeciality findDoctorSpecialityByDoctorId(Integer specialityId) {
		return doctorSpecialityRepository.findOne(specialityId);
	}

	@Override
	public List<DoctorSpeciality> findAllDoctorSpecialityByDoctorId(Integer doctorId) {
		return doctorSpecialityRepository.findAllDoctorSpecialitiesByDoctorId(doctorId);
	}

	@Override
	public void deleteDoctorSpecialityById(Integer id) {
		doctorSpecialityRepository.delete(id);
	}

	@Override
	public DoctorSpeciality saveDoctorSpeciality(DoctorSpeciality doctorSpeciality) {
		return doctorSpecialityRepository.save(doctorSpeciality);
	}
}
