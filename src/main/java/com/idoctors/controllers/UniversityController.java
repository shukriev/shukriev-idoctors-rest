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

import com.idoctors.domain.University;
import com.idoctors.resources.UniversityResource;
import com.idoctors.resources.assemblers.UniversityResourceAssembler;
import com.idoctors.services.UniversityService;
import com.idoctors.validation.Existing;
import com.idoctors.validation.New;

@RestController
@RequestMapping(value = "/university", produces = "application/hal+json")
@ExposesResourceFor(UniversityController.class)
public class UniversityController {
	
	private static final Logger logger = LoggerFactory.getLogger(UniversityController.class);

	@Autowired
	private UniversityService universityService;

	@Autowired
	UniversityResourceAssembler universityResourceAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<UniversityResource>> findAllUniversities() {
		logger.info("Find all universities");

		List<University> universities = new ArrayList<University>();
		try {
			universityService.findAllUniversities().iterator().forEachRemaining(universities::add);
		} catch (NullPointerException ex) {
			logger.error(ex.getMessage());
			
			return new ResponseEntity<List<UniversityResource>>(HttpStatus.NOT_FOUND);
		}
		
		if (universities.isEmpty()) {
			logger.error("There is no university");

			return new ResponseEntity<List<UniversityResource>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<UniversityResource>>(universityResourceAssembler.toResources(universities), HttpStatus.OK);
	}

	@RequestMapping(value = "/{universityId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UniversityResource> getUniversityById(@PathVariable Integer universityId) {
		logger.info("Find university by id: {}", universityId);

		University university = universityService.getUniversityById(universityId);

		if (university == null) {
			return new ResponseEntity<UniversityResource>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UniversityResource>(universityResourceAssembler.toResource(university), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UniversityResource> createUniversity(@Validated(New.class) @RequestBody University university){
		logger.info("Create university with name: {}", university.getName());
		
		University createdUniversity = universityService.saveUniversity(university);
		
		if(createdUniversity == null) {
			logger.error("University cannot be created");
			
			return new ResponseEntity<UniversityResource>(HttpStatus.BAD_REQUEST);
		}
		
		logger.info("University with id: {} has been created", createdUniversity);
		return new ResponseEntity<UniversityResource>(universityResourceAssembler.toResource(createdUniversity), HttpStatus.OK);
	}
	
	public ResponseEntity<Void> deleteUniversity(@PathVariable Integer universityId) {
		logger.info("Delete university with id: {}", universityId);
		
		if(universityService.getUniversityById(universityId) == null) {
			logger.error("Unable to delete. University with id {} not found", universityId);
			
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{universityId}", produces = "application/json", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<UniversityResource> updateUniversity(@PathVariable Integer universityId,
			@Validated(Existing.class) @RequestBody University university) {
		logger.info("Update university with id: ", universityId);
		
		University currentUniversity = universityService.getUniversityById(universityId);

		if (currentUniversity == null) {
			logger.error("Unable to update. University with id {} not found", universityId);
			
			return new ResponseEntity<UniversityResource>(HttpStatus.NOT_FOUND);
		}

		currentUniversity.setName(university.getName());
		currentUniversity.setSpecialities(university.getSpecialities());
		
		universityService.saveUniversity(currentUniversity);
		
		logger.info("University with id {} has been updated", universityId);
		
		return new ResponseEntity<UniversityResource>(universityResourceAssembler.toResource(currentUniversity), HttpStatus.OK);
	}

}
