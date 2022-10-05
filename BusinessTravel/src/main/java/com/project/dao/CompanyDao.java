package com.project.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Company;


public interface CompanyDao extends JpaRepository<Company, Long> {
	
}
