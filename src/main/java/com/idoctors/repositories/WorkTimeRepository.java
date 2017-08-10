package com.idoctors.repositories;

import org.springframework.data.repository.CrudRepository;

import com.idoctors.domain.WorkTime;

public interface WorkTimeRepository extends CrudRepository<WorkTime, Integer> {
}
