package com.idoctors.controllers;


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

import com.idoctors.domain.Doctor;
import com.idoctors.resources.DoctorResource;
import com.idoctors.resources.assemblers.DoctorResourceAssembler;
import com.idoctors.services.DoctorService;
import com.idoctors.validation.Existing;
import com.idoctors.validation.New;

@RestController
@RequestMapping(value = "/doctor", produces = "application/hal+json")
@ExposesResourceFor(DoctorResource.class)
public class DoctorController {
	private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private DoctorResourceAssembler doctorResourceAssembler;
		
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<DoctorResource>> getAllDoctors(){
		List<DoctorResource> doctorsResource = doctorResourceAssembler.toResources(doctorService.listAllDoctors());
		
		return new ResponseEntity<List<DoctorResource>>(doctorsResource, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DoctorResource> getDoctorById(@PathVariable Integer id){
		Doctor doctor = doctorService.getDoctorById(id);
		
		if(doctor == null) {
			logger.error("Doctor does not exist");
			
			return new ResponseEntity<DoctorResource>(HttpStatus.BAD_REQUEST);
		}
		
		DoctorResource doctorResource = doctorResourceAssembler.toResource(doctor);
		
		return new ResponseEntity<DoctorResource>(doctorResource, HttpStatus.OK);
	}
	 
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Integer> createDoctor(@Validated(New.class) @RequestBody Doctor doctor) {
		logger.info("Create doctor with name: {}", doctor.getFirstName() + " " + doctor.getLastName());
		Integer doctorId = doctorService.saveDoctor(doctor);

		if(doctorId == null) {
			logger.error("Doctor cannot be created");
			
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		
		logger.info("Doctor with id {} has been created", doctorId);
		return new ResponseEntity<Integer>(doctorId, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id) {
		logger.info("Deleting user with id: {}", id);
		
			if(doctorService.getDoctorById(id) == null) {
				logger.error("Unable to delete. Docktor with id {} not found", id);
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}

		doctorService.deleteDoctorById(id);
		
		logger.info("Doctor with id {} has been deleted", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", produces = "application/json", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<DoctorResource> updateDoctor(@PathVariable Integer id, @Validated(Existing.class) @RequestBody Doctor doctor){
		logger.info("Update doctor with id: ", id);
		Doctor currentDoctor = doctorService.getDoctorById(id);
		
		if(currentDoctor == null) {
			logger.error("Unable to update. Doctor with id {} not found", id);
			return new ResponseEntity<DoctorResource>(HttpStatus.NOT_FOUND);
		}
		
		currentDoctor.setFirstName(doctor.getFirstName());
		currentDoctor.setLastName(doctor.getLastName());
		currentDoctor.setEmail(doctor.getEmail());
		doctorService.updateDoctor(doctor);
		
		logger.info("Doctor with id {} has been updated", id);
		DoctorResource doctorResource = doctorResourceAssembler.toResource(currentDoctor);
		return new ResponseEntity<DoctorResource>(doctorResource, HttpStatus.OK);
	}
	
	
}
