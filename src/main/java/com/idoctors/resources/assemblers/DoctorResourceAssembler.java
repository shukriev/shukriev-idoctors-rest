/**
 * @author Shukri Shukriev
**/
package com.idoctors.resources.assemblers;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idoctors.controllers.DoctorController;
import com.idoctors.domain.Doctor;
import com.idoctors.resources.DoctorResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DoctorResourceAssembler extends ResourceAssemblerSupport<Doctor, DoctorResource>{
	public static final String DOCTOR_SPECIALITIES_REL = "doctorSpecialities";

	public DoctorResourceAssembler() {
		super(DoctorController.class, DoctorResource.class);
	}

	@Override
	public DoctorResource toResource(Doctor doctor) {
		DoctorResource doctorResource = new DoctorResource(doctor);
		
		Link selfLink = linkTo(methodOn(DoctorController.class).getDoctorById(doctor.getId())).withSelfRel();
		Link doctorSpecialities = linkTo(methodOn(DoctorController.class).findAllDoctorSpecialitiesByDoctorId(doctor.getId())).withRel(DOCTOR_SPECIALITIES_REL);
		
		doctorResource.add(selfLink);
		doctorResource.add(doctorSpecialities);
		
		return doctorResource;
	}

}
