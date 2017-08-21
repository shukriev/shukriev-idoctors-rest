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
@Table(name = "work_time")
public class WorkTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne(mappedBy = "workTime")
	private DoctorWorkSchedule workSchedule;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DoctorWorkSchedule getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(DoctorWorkSchedule workSchedule) {
		this.workSchedule = workSchedule;
	}

	@Override
	public String toString() {
		return "WorkTime [id=" + id + ", workSchedule=" + workSchedule + "]";
	}
	
	
}
