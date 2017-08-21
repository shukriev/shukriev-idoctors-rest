/**
 * @author Shukri Shukriev
 *
 */
package com.idoctors.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.idoctors.validation.Existing;
import com.idoctors.validation.New;

@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@NotNull(groups = Existing.class)
	@Null(groups = New.class)
	private Integer id;

	@Column(name = "first_name")
	@NotNull(message = "First name is required", groups = {New.class, Existing.class})
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	@NotNull(message = "Email is required", groups = {New.class, Existing.class})
	private String email;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private Set<DoctorSpeciality> doctorEducation;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private Set<DoctorWorkSchedule> doctorWorkSchedule;

	public Doctor() {
	}

	public Doctor(Integer id, String firstName, String lastName, String email) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", doctorEducation=" + doctorEducation + ", doctorWorkSchedule=" + doctorWorkSchedule + "]";
	}
	
	public static Builder getBuilder() {
		
		return new Builder();
	}
	
	public static class Builder {
		private Doctor doctor;
		
		public Builder() {
			doctor = new Doctor();
		}
		
		public Builder id(Integer id) {
			doctor.setId(id);
			return this;
		}
		
		public Builder firstName(String firstName) {
			doctor.setFirstName(firstName);
			return this;
		}
		
		public Builder lastName(String lastName) {
			doctor.setLastName(lastName);
			return this;
		}
		
		public Builder email(String email) {
			doctor.setEmail(email);
			return this;
		}
		
		public Builder doctorEducation(Set<DoctorSpeciality> doctorEducation) {
			doctor.setDoctorEducation(doctorEducation);
			return this;
		}
		
		public Builder doctorWorkSchedule(Set<DoctorWorkSchedule> doctorWorkSchedule) {
			doctor.setDoctorWorkSchedule(doctorWorkSchedule);
			return this;
		}
		
		public Doctor build() {
			return doctor;
		}
	}
	
}
