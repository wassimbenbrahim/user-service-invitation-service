package com.project.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "company")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Company extends User {

	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String desc;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	 private List<Employee> employees = new ArrayList<>();
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	 private List<Invitation> invitations = new ArrayList<>();
	
	@Override
	public String toString() { return "Company [name=" + super.getUsername() + ", desc=" + desc + ",  employees=" + employees +", invitations=" + invitations + "]"; }

}
