package com.idoctors.repositories;

import org.springframework.data.repository.CrudRepository;

import com.idoctors.domain.Hospital;

public interface HospitalRepository extends CrudRepository<Hospital, Integer> {
}
