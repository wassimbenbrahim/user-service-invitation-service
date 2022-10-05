package com.project.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Employee extends User {

	private static final long serialVersionUID = 1L;

	@Column(name = "sex")
	private String sex;
	
	@Column(name="profession")
    private String profession;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_comp")
	@JsonIgnore
	private Company company;

	
	@Override
	public String toString() {
		return "Employee [sex=" + sex +", profession=" + profession +", company=" + company + "]";
	}

}
