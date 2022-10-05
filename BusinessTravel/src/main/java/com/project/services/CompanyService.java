package com.project.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.entities.Company;

@Service
@Transactional

public interface CompanyService {
	
	public void add(Company company );
	public void update (Company company) ;
	public void delete (Long id );
	public java.util.Optional<Company> find ( Long id );
	public List <Company>findAll();

}
