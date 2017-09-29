/**
 * @author Shukri Shukriev
**/
package com.idoctors.resources;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.idoctors.domain.Doctor;
import com.idoctors.domain.DoctorSpeciality;
import com.idoctors.domain.Speciality;
import com.idoctors.domain.University;

@Relation(value = "doctorSpeciality", collectionRelation = "doctorSpecialities")
public class DoctorSpecialityResource extends ResourceSupport {
	//Id is skipped
	private Doctor doctor;
	private Speciality speciality;
	private University university;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
	
	@JsonCreator
	public DoctorSpecialityResource(DoctorSpeciality doctorSpeciality) {
		setDoctor(doctorSpeciality.getDoctor());
		setSpeciality(doctorSpeciality.getSpeciality());
	}
	
	@JsonCreator
	public DoctorSpecialityResource(@JsonProperty("doctor") Doctor doctor, @JsonProperty("speciality") Speciality speciality, @JsonProperty("university") University university) {
		super();
		setDoctor(doctor);
		setSpeciality(speciality);
		setUniversity(university);
	}
}
