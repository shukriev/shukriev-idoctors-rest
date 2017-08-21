package com.idoctors.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.idoctors.domain.DoctorSpeciality;

public interface DoctorSpecialityRepository extends CrudRepository<DoctorSpeciality, Integer> {
	List<DoctorSpeciality> findAllDoctorEducationByDoctorId(Integer doctorId);
}
