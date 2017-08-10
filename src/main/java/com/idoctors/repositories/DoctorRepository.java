package com.idoctors.repositories;

import org.springframework.data.repository.CrudRepository;

import com.idoctors.domain.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
}
