/**
 * @author Shukri Shukriev
**/
package com.idoctors.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "speciality")
public class Speciality {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "doctorSpeciality")
	private DoctorSpeciality doctorSpeciality;

	@OneToMany(mappedBy = "university")
	private University university;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DoctorSpeciality getDoctorSpeciality() {
		return doctorSpeciality;
	}

	public void setDoctorSpeciality(DoctorSpeciality doctorSpeciality) {
		this.doctorSpeciality = doctorSpeciality;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Override
	public String toString() {
		return "Speciality [id=" + this.id + ", name=" + this.name + ", university=" + this.university + ", doctorSpeciality=" + this.doctorSpeciality + "]";
	}
}
