/**
 * @author Shukri Shukriev
**/
package com.idoctors.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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

	@OneToOne(mappedBy = "speciality")
	private DoctorSpeciality doctorEducation;

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

	public DoctorSpeciality getDoctorEducation() {
		return doctorEducation;
	}

	public void setDoctorEducation(DoctorSpeciality doctorEducation) {
		this.doctorEducation = doctorEducation;
	}

	@Override
	public String toString() {
		return "Speciality [id=" + id + ", name=" + name + ", doctorEducation=" + doctorEducation + "]";
	}
	
	
}
