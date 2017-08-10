package com.idoctors.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idoctors.domain.DoctorEducation;
import com.idoctors.repositories.DoctorEducationRepository;
import com.idoctors.services.DoctorEducationService;

@Service
public class DoctorEducationServiceImpl implements DoctorEducationService {

	@Autowired
	private DoctorEducationRepository repository;
	
	@Override
	public Iterable<DoctorEducation> listAllniversities() {
		return repository.findAll();
	}

	@Override
	public DoctorEducation getDoctorEducationByDoctorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoctorEducation saveDoctor(DoctorEducation doctorEducation) {
		// TODO Auto-generated method stub
		return null;
	}


}
