/**
 * @author Shukri Shukriev
**/
package com.idoctors.resources.assemblers;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.idoctors.controllers.HospitalController;
import com.idoctors.domain.Hospital;
import com.idoctors.resources.HospitalResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class HospitalResourceAssembler extends ResourceAssemblerSupport<Hospital, HospitalResource>{
	
	public HospitalResourceAssembler() {
		super(HospitalController.class, HospitalResource.class);
	}

	@Override
	public HospitalResource toResource(Hospital hospital) {
		HospitalResource hospitalResource = new HospitalResource(hospital);
		
		Link selfLink = linkTo(methodOn(HospitalController.class).getHospitalById(hospital.getId())).withSelfRel();		
		hospitalResource.add(selfLink);
		
		return hospitalResource;
	}

}
