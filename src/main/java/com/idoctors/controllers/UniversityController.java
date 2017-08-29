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

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<University>> findAllUniversities() {
		logger.info("Find all universities");

		List<University> universities = new ArrayList<University>();
		universityService.findAllUniversities().iterator().forEachRemaining(universities::add);

		if (universities.isEmpty()) {
			logger.error("There is no university");

			return new ResponseEntity<List<University>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<University>>(universities, HttpStatus.OK);
	}

	@RequestMapping(value = "/{universityId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<University> getUniversityById(@PathVariable Integer universityId) {
		logger.info("Find university by id: {}", universityId);

		University university = universityService.getUniversityById(universityId);

		if (university == null) {
			return new ResponseEntity<University>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<University>(university, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<University> createUniversity(@Validated(New.class) @RequestBody University university){
		logger.info("Create university with name: {}", university.getName());
		
		University createdUniversity = universityService.saveUniversity(university);
		if(createdUniversity == null) {
			logger.error("University cannot be created");
			
			return new ResponseEntity<University>(HttpStatus.BAD_REQUEST);
		}
		
		logger.info("University with id: {} has been created", createdUniversity.getId());
		return new ResponseEntity<University>(createdUniversity, HttpStatus.OK);
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
	public ResponseEntity<University> updateUniversity(@PathVariable Integer universityId,
			@Validated(Existing.class) @RequestBody University university) {
		logger.info("Update university with id: ", universityId);
		
		University currentUniversity = universityService.getUniversityById(universityId);

		if (currentUniversity == null) {
			logger.error("Unable to update. University with id {} not found", universityId);
			
			return new ResponseEntity<University>(HttpStatus.NOT_FOUND);
		}

		currentUniversity.setName(university.getName());

		universityService.saveUniversity(currentUniversity);
		
		logger.info("University with id {} has been updated", universityId);
		
		return new ResponseEntity<University>(currentUniversity, HttpStatus.OK);
	}

}
