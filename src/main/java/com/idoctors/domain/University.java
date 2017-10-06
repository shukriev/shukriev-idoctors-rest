package com.idoctors.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.idoctors.validation.Existing;
import com.idoctors.validation.New;

@Entity
@Table(name = "university")
public class University {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@NotNull(groups = Existing.class)
	@Null(groups = New.class)
	private Integer id;
	
	@Column(name="name")
	@NotNull(message = "University name is required", groups = {New.class, Existing.class})
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "university")
	private Set<Speciality> specialities;

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

	public Set<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Set<Speciality> specialities) {
		this.specialities = specialities;
	}

	@Override
	public String toString() {
		return "University [id=" + getId() + ", name=" + getName() +  "]";
	}
}
