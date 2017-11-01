/**
 * @author Shukri Shukriev
**/
package com.idoctors.resources;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import com.idoctors.domain.Speciality;
import com.idoctors.domain.University;


@Relation(value = "university", collectionRelation = "universities")
public class UniversityResource extends ResourceSupport {
	private String name;
	private Set<Speciality> specialities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Set<Speciality> specialities) {
		this.specialities = specialities;
	}

	@JsonCreator
	public UniversityResource(University university) {
		setName(university.getName());
		setSpecialities(university.getSpecialities());
	}

	@JsonCreator
	public UniversityResource(@JsonProperty("name") String name, 
			@JsonProperty("specialities") Set<Speciality> specialities) {
		super();
		setName(name);
		setSpecialities(specialities);
	}
}
