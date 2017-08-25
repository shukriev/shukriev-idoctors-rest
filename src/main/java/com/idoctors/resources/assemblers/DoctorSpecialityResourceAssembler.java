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
	public static final String DOCTOR_REL = "doctor";
	
	public DoctorSpecialityResourceAssembler() {
		super(DoctorSpeciality.class, DoctorSpecialityResource.class);
	}

	@Override
	public DoctorSpecialityResource toResource(DoctorSpeciality doctorSpeciality) {
		DoctorSpecialityResource doctorSpecialityReource = new DoctorSpecialityResource(doctorSpeciality);
		
		Link selfLink = linkTo(methodOn(DoctorController.class).findDoctorSpecialityById(doctorSpeciality.getDoctor().getId(), doctorSpeciality.getSpeciality().getId())).withSelfRel();
		Link doctorLink = linkTo(methodOn(DoctorController.class).getDoctorById(doctorSpeciality.getDoctor().getId())).withRel(DOCTOR_REL);
		
		doctorSpecialityReource.add(selfLink);
		doctorSpecialityReource.add(doctorLink);
		
		return doctorSpecialityReource;
	}
	
	
}
