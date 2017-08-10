package com.idoctors.service.impl;

import com.idoctors.domain.Speciality;
import com.idoctors.repositories.SpecialityRepository;
import com.idoctors.services.SpecialityService;

public class SpecialityServiceImpl implements SpecialityService{
	
	SpecialityRepository specialityRepository;
	
	@Override
	public Iterable<Speciality> listAllSpecialities() {
		return specialityRepository.findAll();
	}

	@Override
	public Speciality getSpecialityById(Integer id) {
		return specialityRepository.findOne(id);
	}

	@Override
	public Speciality saveSpeciality(Speciality speciality) {
		return specialityRepository.save(speciality);
	}

}
