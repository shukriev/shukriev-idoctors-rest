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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.idoctors.validation.Existing;
import com.idoctors.validation.New;

@Entity
@Table(name = "hospital")
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@NotNull(groups = Existing.class)
	@Null(groups = New.class)
	private Integer id;

	@Column(name = "name")
	@NotNull(message = "Hospital name", groups = {New.class, Existing.class})
	private String name;

	@Column(name = "address")
	@NotNull(message = "Hospital address", groups = {New.class, Existing.class})
	private String address;

	@Column(name = "city")
	@NotNull(message = "Hospital city", groups = {New.class, Existing.class})
	private String city;

	@Column(name = "website")
	private String website;

	@Column(name = "isHospital")
	private boolean isHospital;

	@Column(name = "isDeleted")
	private boolean isDeleted;

	@OneToOne(mappedBy = "workTime")
	private DoctorWorkSchedule workSchedule;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public boolean getIsHospital() {
		return isHospital;
	}

	public void setIsHospital(boolean isHospital) {
		this.isHospital = isHospital;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
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
