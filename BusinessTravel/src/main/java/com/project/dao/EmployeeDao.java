package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Employee;

public interface EmployeeDao  extends JpaRepository<Employee, Long >{
	
	

}
