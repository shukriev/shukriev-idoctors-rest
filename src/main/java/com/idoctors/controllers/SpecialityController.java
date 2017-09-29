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

import com.idoctors.domain.Speciality;
import com.idoctors.services.SpecialityService;
import com.idoctors.validation.Existing;
import com.idoctors.validation.New;

@RestController
@RequestMapping(value = "/speciality", produces = "application/hal+json")
@ExposesResourceFor(SpecialityController.class)
public class SpecialityController {
	private static final Logger logger = LoggerFactory.getLogger(SpecialityController.class);

	@Autowired
	private SpecialityService specialityService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Speciality>> findAllSpecialities() {
		logger.info("Find all specialities");

		List<Speciality> specialities = new ArrayList<Speciality>();
		specialityService.findAllSpecialities().iterator().forEachRemaining(specialities::add);

		if (specialities.isEmpty()) {
			logger.error("There is no speciality");

			return new ResponseEntity<List<Speciality>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Speciality>>(specialities, HttpStatus.OK);
	}

	@RequestMapping(value = "/{specialityId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Speciality> getSpecialityById(@PathVariable Integer specialityId) {
		logger.info("Find speciality by id: {}", specialityId);

		Speciality speciality = specialityService.getSpecialityById(specialityId);

		if (speciality == null) {
			return new ResponseEntity<Speciality>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Speciality>(speciality, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Speciality> createSpeciality(@Validated(New.class) @RequestBody Speciality speciality){
		logger.info("Create speciality with name: {}", speciality.getName());
		
		Speciality createdSpeciality = specialityService.saveSpeciality(speciality);
		if(createdSpeciality == null) {
			logger.error("Speciality cannot be created");
			
			return new ResponseEntity<Speciality>(HttpStatus.BAD_REQUEST);
		}
		
		logger.info("Speciality with id: {} has been created", createdSpeciality.getId());
		return new ResponseEntity<Speciality>(createdSpeciality, HttpStatus.OK);
	}
	
	public ResponseEntity<Void> deleteSpeciality(@PathVariable Integer specialityId) {
		logger.info("Delete speciality with id: {}", specialityId);
		
		if(specialityService.getSpecialityById(specialityId) == null) {
			logger.error("Unable to delete. Speciality with id {} not found", specialityId);
			
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{specialityId}", produces = "application/json", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Speciality> updateSpeciality(@PathVariable Integer specialityId,
			@Validated(Existing.class) @RequestBody Speciality speciality) {
		logger.info("Update speciality with id: ", specialityId);
		Speciality currentSpeciality = specialityService.getSpecialityById(specialityId);

		if (currentSpeciality == null) {
			logger.error("Unable to update. Speciality with id {} not found", specialityId);
			
			return new ResponseEntity<Speciality>(HttpStatus.NOT_FOUND);
		}

		currentSpeciality.setName(speciality.getName());
//		TODO
//		Re-think functionality
//		currentSpeciality.setDoctorSpeciality(speciality.getDoctorSpeciality());
		
		specialityService.saveSpeciality(currentSpeciality);
		
		logger.info("Speciality with id {} has been updated", specialityId);
		
		return new ResponseEntity<Speciality>(currentSpeciality, HttpStatus.OK);
	}

}
