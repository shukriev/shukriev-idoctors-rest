/**
 * @author Shukri Shukriev
**/
package com.idoctors.resources;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.idoctors.domain.DoctorWorkSchedule;
import com.idoctors.domain.Hospital;


@Relation(value = "hospital", collectionRelation = "hospitals")
public class HospitalResource extends ResourceSupport {
	private String name;
	private String address;
	private String city;
	private String website;
	private boolean isHospital;
	private boolean isDeleted;
	private DoctorWorkSchedule workSchedule;

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

	public void setIsHospital(boolean isHospital) {
		this.isHospital = isHospital;
	}

	public boolean isDeleted() {
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

	@JsonCreator
	public HospitalResource(Hospital hospital) {
		setName(hospital.getName());
		setAddress(hospital.getAddress());
		setCity(hospital.getCity());
		setWebsite(hospital.getWebsite());
		setIsHospital(hospital.getIsHospital());
		setIsDeleted(hospital.getIsDeleted());
		setWorkSchedule(hospital.getWorkSchedule());
	}

	
	@JsonCreator
	public HospitalResource(@JsonProperty("name") String name, @JsonProperty("address") String address, 
			@JsonProperty("city") String city, @JsonProperty("website") String website, @JsonProperty("isHospital") boolean isHospital, 
			@JsonProperty("isDeleted") boolean isDeleted, @JsonProperty("workSchedule") DoctorWorkSchedule workSchedule) {
		super();
		
		setName(name);
		setAddress(address);
		setCity(city);
		setWebsite(website);
		setIsHospital(isHospital);
		setIsDeleted(isDeleted);
		setWorkSchedule(workSchedule);
	}
}
