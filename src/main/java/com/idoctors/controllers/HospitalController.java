/**
 * @author Shukri Shukriev
**/
package com.idoctors.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.idoctors.domain.Hospital;
import com.idoctors.resources.HospitalResource;
import com.idoctors.resources.assemblers.HospitalResourceAssembler;
import com.idoctors.services.HospitalService;
import com.idoctors.validation.Existing;
import com.idoctors.validation.New;

@RestController
@RequestMapping(value = "/hospital", produces = "application/hal+json")
@ExposesResourceFor(HospitalController.class)
public class HospitalController {
	
	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	HospitalResourceAssembler hospitalResourceAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<HospitalResource>> findAllHospitals() {
		logger.info("Find all hospitals");

		List<Hospital> hospitals = new ArrayList<Hospital>();
		
		try {
			hospitalService.findAllHospitals().iterator().forEachRemaining(hospitals::add);
		} catch (NullPointerException ex) {
			logger.error(ex.getMessage());
			
			return new ResponseEntity<List<HospitalResource>>(HttpStatus.NOT_FOUND);
		}
		
		if (hospitals.isEmpty()) {
			logger.error("There is no hospital");

			return new ResponseEntity<List<HospitalResource>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<HospitalResource>>(hospitalResourceAssembler.toResources(hospitals), HttpStatus.OK);
	}

	@RequestMapping(value = "/{hospitalId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<HospitalResource> getHospitalById(@PathVariable Integer hospitalId) {
		logger.info("Find hospital by id: {}", hospitalId);

		Hospital hospital = hospitalService.getHospitalById(hospitalId);

		if (hospital == null) {
			return new ResponseEntity<HospitalResource>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<HospitalResource>(hospitalResourceAssembler.toResource(hospital), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HospitalResource> createHospital(@Validated(New.class) @RequestBody Hospital hospital){
		logger.info("Create hospital with name: {}", hospital.getName());
		
		Hospital createdHospital = hospitalService.saveHospital(hospital);
		
		if(createdHospital == null) {
			logger.error("Hospital cannot be created");
			
			return new ResponseEntity<HospitalResource>(HttpStatus.BAD_REQUEST);
		}
		
		logger.info("Hospital with id: {} has been created", createdHospital);
		return new ResponseEntity<HospitalResource>(hospitalResourceAssembler.toResource(createdHospital), HttpStatus.OK);
	}
	
	public ResponseEntity<Void> deleteHospital(@PathVariable Integer hospitalId) {
		logger.info("Delete hospital with id: {}", hospitalId);
		
		if(hospitalService.getHospitalById(hospitalId) == null) {
			logger.error("Unable to delete. Hospital with id {} not found", hospitalId);
			
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{hospitalId}", produces = "application/json", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<HospitalResource> updateUniversity(@PathVariable Integer hospitalId,
			@Validated(Existing.class) @RequestBody Hospital hospital) {
		logger.info("Update hospital with id: ", hospitalId);
		
		Hospital currentHospital = hospitalService.getHospitalById(hospitalId);

		if (currentHospital == null) {
			logger.error("Unable to update. Hospital with id {} not found", hospitalId);
			
			return new ResponseEntity<HospitalResource>(HttpStatus.NOT_FOUND);
		}

		currentHospital.setName(hospital.getName());
		currentHospital.setAddress(hospital.getAddress());
		currentHospital.setCity(hospital.getCity());
		currentHospital.setWebsite(hospital.getWebsite());
		currentHospital.setIsHospital(hospital.getIsHospital());
		currentHospital.setIsDeleted(hospital.getIsDeleted());
		currentHospital.setWorkSchedule(hospital.getWorkSchedule());
		
		hospitalService.saveHospital(currentHospital);
		
		logger.info("Hospital with id {} has been updated", hospitalId);
		
		return new ResponseEntity<HospitalResource>(hospitalResourceAssembler.toResource(currentHospital), HttpStatus.OK);
	}

}
