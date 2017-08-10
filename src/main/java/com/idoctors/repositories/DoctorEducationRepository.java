package com.idoctors.repositories;

import org.springframework.data.repository.CrudRepository;

import com.idoctors.domain.DoctorEducation;

public interface DoctorEducationRepository extends CrudRepository<DoctorEducation, Integer> {
}
