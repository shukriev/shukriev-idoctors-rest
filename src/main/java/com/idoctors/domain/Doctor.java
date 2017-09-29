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

	@Column(name = "firstName")
	@NotNull(message = "First name is required", groups = {New.class, Existing.class})
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "email")
	@NotNull(message = "Email is required", groups = {New.class, Existing.class})
	private String email;

	@OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL)
	private Set<DoctorSpeciality> doctorSpecialities;

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

	public Set<DoctorSpeciality> getDoctorSpecialities() {
		return doctorSpecialities;
	}

	public void setDoctorSpecialities(Set<DoctorSpeciality> doctorSpecialities) {
		this.doctorSpecialities = doctorSpecialities;
	}

	public Set<DoctorWorkSchedule> getDoctorWorkSchedule() {
		return doctorWorkSchedule;
	}

	public void setDoctorWorkSchedule(Set<DoctorWorkSchedule> doctorWorkSchedule) {
		this.doctorWorkSchedule = doctorWorkSchedule;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", email=" + getEmail()
				+ ", doctorEducation=" + getDoctorSpecialities() + ", doctorWorkSchedule=" + getDoctorWorkSchedule() + "]";
	}
	
	public static Builder getBuilder() {
		
		return new Builder();
	}
	
	public static class Builder {
		private Doctor doctor;
		
		public Builder() {
			doctor = new Doctor();
		}
		
		public Builder doctorId(Integer doctorId) {
			doctor.setId(doctorId);
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
		
		public Builder doctorEducation(Set<DoctorSpeciality> doctorSpecialities) {
			doctor.setDoctorSpecialities(doctorSpecialities);
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
