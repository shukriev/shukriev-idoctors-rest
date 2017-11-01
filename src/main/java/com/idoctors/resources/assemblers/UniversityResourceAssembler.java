/**
 * @author Shukri Shukriev
**/
package com.idoctors.resources.assemblers;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idoctors.controllers.UniversityController;
import com.idoctors.domain.University;
import com.idoctors.resources.UniversityResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UniversityResourceAssembler extends ResourceAssemblerSupport<University, UniversityResource>{
	public static final String DOCTOR_SPECIALITIES_REL = "doctorSpecialities";

	public UniversityResourceAssembler() {
		super(UniversityController.class, UniversityResource.class);
	}

	@Override
	public UniversityResource toResource(University university) {
		UniversityResource universityResource = new UniversityResource(university);
		
		Link selfLink = linkTo(methodOn(UniversityController.class).getUniversityById(university.getId())).withSelfRel();		
		universityResource.add(selfLink);
		
		return universityResource;
	}

}
