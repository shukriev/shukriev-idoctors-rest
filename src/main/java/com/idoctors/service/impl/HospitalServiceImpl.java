/**
 * @author Shukri Shukriev
**/
package com.idoctors.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idoctors.domain.Hospital;
import com.idoctors.repositories.HospitalRepository;
import com.idoctors.services.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {
	@Autowired
	private HospitalRepository repository;

	@Override
	public Iterable<Hospital> findAllHospitals() {
		return repository.findAll();
	}

	@Override
	public Hospital getHospitalById(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public Hospital saveHospital(Hospital hospital) {
		return repository.save(hospital);
	}

	@Override
	public void deleteHospital(Integer id) {
		repository.delete(id);
	}
}
