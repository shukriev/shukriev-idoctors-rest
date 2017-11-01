/**
 * @author Shukri Shukriev
**/
package com.idoctors.resources.assemblers;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idoctors.controllers.DoctorController;
import com.idoctors.domain.DoctorSpeciality;
import com.idoctors.resources.DoctorSpecialityResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;

@Component
public class DoctorSpecialityResourceAssembler extends ResourceAssemblerSupport<DoctorSpeciality, DoctorSpecialityResource>{

	public DoctorSpecialityResourceAssembler() {
		super(DoctorSpeciality.class, DoctorSpecialityResource.class);
	}

	@Override
	public DoctorSpecialityResource toResource(DoctorSpeciality doctorSpeciality) {
		DoctorSpecialityResource doctorSpecialityReource = new DoctorSpecialityResource(doctorSpeciality);
		
		Link selfLink = linkTo(methodOn(DoctorController.class).findDoctorSpecialityById(doctorSpeciality.getDoctor().getId(), doctorSpeciality.getSpeciality().getId())).withSelfRel();
		
		doctorSpecialityReource.add(selfLink);
		
		return doctorSpecialityReource;
	}
	
	
}
