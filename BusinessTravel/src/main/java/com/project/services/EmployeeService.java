package com.project.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.entities.Employee;

@Service
@Transactional

public interface EmployeeService {
	public void add (Employee emp);
	public void update ( Employee emp);
	public java.util.Optional<Employee> find (Long  id );
	public void delete (Long  id );
	public List <Employee > findAll();

}
