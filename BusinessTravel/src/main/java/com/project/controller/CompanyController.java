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

import com.project.entities.Company;
import com.project.services.CompanyService;

@RestController
@RequestMapping(value="company")
public class CompanyController {
	@Autowired
	private CompanyService  service ;
	
	@PostMapping(value="/create")
	public void add(@RequestBody Company company ) {
		service.add(company);
	}
	
	@PutMapping(value="/update")
	public void update(@RequestBody Company company  ) {
		service.update(company);
	}
	
	@GetMapping(value="/find/{id}")
	public Optional<Company> find(@PathVariable("id")Long id ) {
		return service.find(id);
	}
	
	@GetMapping(value="/all")
	public List<Company> findAll() {
		return service.findAll();
	}
	
	@DeleteMapping(value="/delete/{id}")
	public void delete(@PathVariable("id") Long id ) {
		service.delete(id);
	}

}
