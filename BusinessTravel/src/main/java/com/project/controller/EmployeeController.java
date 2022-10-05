package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Employee;
import com.project.services.EmployeeService;

@RestController
@RequestMapping(value="/api")
public class EmployeeController {
	@Autowired
	private EmployeeService  service ;
	
	@PostMapping(value="employee/save")
	public void add(@RequestBody  Employee employee) {
		service.add(employee);
	}
	
	@PutMapping(value="employee/update")
	public void update(@RequestBody Employee employee ) {
		service.update(employee);	
	}
	
	@GetMapping(value="/employee/{id}")
	public Optional<Employee> find(@PathVariable("id")Long id ) {
		return service.find(id);
	}
	
	@GetMapping(value="/employees")
	public List<Employee> findAll() {
		return service.findAll();
	}
	
	@DeleteMapping(value="employee/delete/{id}")
	public void delete(@PathVariable("id") Long id ) {
		service.delete(id);
	}
}
