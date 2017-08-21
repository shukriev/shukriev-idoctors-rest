/**
 * @author Shukri Shukriev
**/
package com.idoctors.resources;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.idoctors.domain.Doctor;
import com.idoctors.domain.DoctorSpeciality;
import com.idoctors.domain.DoctorWorkSchedule;

@Relation(value="doctor", collectionRelation="doctors")
public class DoctorResource extends ResourceSupport {
	//Id is skipped, because it comes from ResourceSupport class as a Link
	private String firstName;
	private String lastName;
	private String email;
	private Set<DoctorSpeciality> doctorEducation;
	private Set<DoctorWorkSchedule> doctorWorkSchedule;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<DoctorSpeciality> getDoctorEducation() {
		return doctorEducation;
	}
	public void setDoctorEducation(Set<DoctorSpeciality> doctorEducation) {
		this.doctorEducation = doctorEducation;
	}
	public Set<DoctorWorkSchedule> getDoctorWorkSchedule() {
		return doctorWorkSchedule;
	}
	public void setDoctorWorkSchedule(Set<DoctorWorkSchedule> doctorWorkSchedule) {
		this.doctorWorkSchedule = doctorWorkSchedule;
	}
	
	@JsonCreator
	public DoctorResource(Doctor doctor) {
		setFirstName(doctor.getFirstName());
		setLastName(doctor.getLastName());
		setEmail(doctor.getEmail());
		setDoctorEducation(doctor.getDoctorEducation());
	}
	
	@JsonCreator
	public DoctorResource(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, 
			@JsonProperty("email") String email, @JsonProperty("doctorEducation")  Set<DoctorSpeciality> doctorEducation,
			Set<DoctorWorkSchedule> doctorWorkSchedule) {
		super();
		
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setDoctorEducation(doctorEducation);
	}
}
