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
@Table(name = "hospital")
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "website")
	private String website;

	@Column(name = "isHospital")
	private boolean isHospital;

	@Column(name = "isDeleted")
	private boolean isDeleted;

	@OneToOne(mappedBy = "workTime")
	private DoctorWorkSchedule workSchedule;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public boolean isHospital() {
		return isHospital;
	}

	public void setHospital(boolean isHospital) {
		this.isHospital = isHospital;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public DoctorWorkSchedule getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(DoctorWorkSchedule workSchedule) {
		this.workSchedule = workSchedule;
	}

	@Override
	public String toString() {
		return "Hospital [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", website="
				+ website + ", isHospital=" + isHospital + ", isDeleted=" + isDeleted + ", workSchedule=" + workSchedule
				+ "]";
	}
}
