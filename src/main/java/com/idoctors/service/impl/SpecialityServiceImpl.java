/**
 * @author Shukri Shukriev
**/
package com.idoctors.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idoctors.domain.Speciality;
import com.idoctors.repositories.SpecialityRepository;
import com.idoctors.services.SpecialityService;

@Service
public class SpecialityServiceImpl implements SpecialityService{
	@Autowired
	SpecialityRepository specialityRepository;
	
	@Override
	public Iterable<Speciality> findAllSpecialities() {
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
