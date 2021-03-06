/**
 * @author Shukri Shukriev
**/
package com.idoctors.services;

import com.idoctors.domain.Hospital;

public interface HospitalService {
	
	Iterable<Hospital> findAllHospitals();

	Hospital getHospitalById(Integer id);

	Hospital saveHospital(Hospital hospital);
	
	void deleteHospital(Integer id);
}
