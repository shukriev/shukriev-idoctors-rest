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

import com.idoctors.domain.Doctor;
import com.idoctors.domain.DoctorSpeciality;
import com.idoctors.domain.Speciality;
import com.idoctors.resources.DoctorResource;
import com.idoctors.resources.DoctorSpecialityResource;
import com.idoctors.resources.assemblers.DoctorResourceAssembler;
import com.idoctors.resources.assemblers.DoctorSpecialityResourceAssembler;
import com.idoctors.services.DoctorService;
import com.idoctors.services.DoctorSpecialityService;
import com.idoctors.services.SpecialityService;
import com.idoctors.validation.Existing;
import com.idoctors.validation.New;

@RestController
@ExposesResourceFor(DoctorResource.class)
@RequestMapping(value = "/doctor", produces = "application/hal+json")
public class DoctorController {
	private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private SpecialityService specialityService;

	@Autowired
	private DoctorSpecialityService doctorSpecialityService;

	@Autowired
	private DoctorResourceAssembler doctorResourceAssembler;

	@Autowired
	private DoctorSpecialityResourceAssembler doctorSpecialityResourceAssembler;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<DoctorResource>> findAllDoctors() {
		logger.info("Find all doctors");

		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			doctorService.findAllDoctors().iterator().forEachRemaining(doctors::add);
		} catch (NullPointerException ex) {
			logger.error(ex.getMessage());
			
			return new ResponseEntity<List<DoctorResource>>(HttpStatus.NOT_FOUND);
		}
		
		if (doctors.isEmpty()) {
			logger.error("There is no doctors");

			return new ResponseEntity<List<DoctorResource>>(HttpStatus.NOT_FOUND);
		}		
		
		List<DoctorResource> doctorsResource = doctorResourceAssembler.toResources(doctorService.findAllDoctors());
		return new ResponseEntity<List<DoctorResource>>(doctorsResource, HttpStatus.OK);
	}

	@RequestMapping(value = "/{doctorId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DoctorResource> getDoctorById(@PathVariable Integer doctorId) {
		Doctor doctor = doctorService.getDoctorById(doctorId);

		if (doctor == null) {
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

		if (doctorId == null) {
			logger.error("Doctor cannot be created");

			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}

		logger.info("Doctor with id {} has been created", doctorId);
		return new ResponseEntity<Integer>(doctorId, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{doctorId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> deleteDoctor(@PathVariable Integer doctorId) {
		logger.info("Deleting user with id: {}", doctorId);

		if (doctorService.getDoctorById(doctorId) == null) {
			logger.error("Unable to delete. Docktor with id {} not found", doctorId);
			
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		doctorService.deleteDoctorById(doctorId);

		logger.info("Doctor with id {} has been deleted", doctorId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{doctorId}", produces = "application/json", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<DoctorResource> updateDoctor(@PathVariable Integer doctorId,
			@Validated(Existing.class) @RequestBody Doctor doctor) {
		logger.info("Update doctor with id: ", doctorId);
		Doctor currentDoctor = doctorService.getDoctorById(doctorId);

		if (currentDoctor == null) {
			logger.error("Unable to update. Doctor with id {} not found", doctorId);
			
			return new ResponseEntity<DoctorResource>(HttpStatus.NOT_FOUND);
		}

		currentDoctor.setFirstName(doctor.getFirstName());
		currentDoctor.setLastName(doctor.getLastName());
		currentDoctor.setEmail(doctor.getEmail());
		
		doctorService.updateDoctor(doctor);

		logger.info("Doctor with id {} has been updated", doctorId);
		DoctorResource doctorResource = doctorResourceAssembler.toResource(currentDoctor);
		return new ResponseEntity<DoctorResource>(doctorResource, HttpStatus.OK);
	}

	@RequestMapping(value = "{doctorId}/speciality/{specialityId}", method = RequestMethod.GET)
	public ResponseEntity<List<DoctorSpecialityResource>> findDoctorSpecialityById(@PathVariable Integer doctorId,
			@PathVariable Integer specialityId) {
		logger.info("Find doctor education by doctor id = {} and speciality id = {}", doctorId, specialityId);
		List<DoctorSpecialityResource> doctorSpecialities = null;

		// get doctors doctorSpecialities=?
		return new ResponseEntity<List<DoctorSpecialityResource>>(doctorSpecialities, HttpStatus.OK);

	}

	@RequestMapping(value = "{doctorId}/speciality", method = RequestMethod.GET)
	public ResponseEntity<List<DoctorSpecialityResource>> findAllDoctorSpecialitiesByDoctorId(
			@PathVariable Integer doctorId) {
		logger.info("Find all doctor speciality");
		List<DoctorSpecialityResource> doctorSpecialities = doctorSpecialityResourceAssembler
				.toResources(doctorSpecialityService.findAllDoctorSpecialityByDoctorId(doctorId));
		
		if(doctorSpecialities.isEmpty()) {
			logger.error("Doctor specialities cannot be found!");
			
			return new ResponseEntity<List<DoctorSpecialityResource>>(new ArrayList<DoctorSpecialityResource>(), HttpStatus.OK);
		}
		
		return new ResponseEntity<List<DoctorSpecialityResource>>(doctorSpecialities, HttpStatus.OK);
	}

	@RequestMapping(value = "{doctorId}/speciality", method = RequestMethod.POST)
	public ResponseEntity<DoctorSpecialityResource> addDoctorSpeciality(@PathVariable Integer doctorId,
			@RequestBody DoctorSpeciality doctorSpeciality) {
		logger.info("Add doctor speciality on doctor with id {}", doctorId);
		
		Doctor currentDoctor = doctorService.getDoctorById(doctorId);
		Speciality currentSpeciality = specialityService.getSpecialityById(doctorSpeciality.getSpeciality().getId());
		
		doctorSpeciality.setSpeciality(currentSpeciality);
		doctorSpeciality.setDoctor(currentDoctor);
		
		return new ResponseEntity<DoctorSpecialityResource>(doctorSpecialityResourceAssembler.toResource(doctorSpecialityService.saveDoctorSpeciality(doctorSpeciality)), HttpStatus.OK);
	}
}
