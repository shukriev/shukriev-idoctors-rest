/**
 * @author Shukri Shukriev
**/
package com.idoctors.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.idoctors.validation.Existing;
import com.idoctors.validation.New;

@Entity
@Table(name = "speciality")
public class Speciality {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@NotNull(groups = Existing.class)
	@Null(groups = New.class)
	private Integer id;

	@Column(name = "name")
	@NotNull(message = "Speciality name is required", groups = {New.class, Existing.class})
	private String name;
	
//	@ManyToOne
//	@JoinColumn(name="universityId", nullable = true)
//	@Null(groups = {New.class, Existing.class})
//	private University university;

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

//	public University getUniversity() {
//		return university;
//	}
//
//	public void setUniversity(University university) {
//		this.university = university;
//	}

	@Override
	public String toString() {
		return "Speciality [id=" + getId() + ", name=" + getName() + "]";
	}
}
