package com.project.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CompanyDao;
import com.project.entities.Company;
import com.project.services.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao dao;

	@Override
	public void add(Company company) {
		dao.save(company);
	}

	@Override
	public void update(Company company) {
		dao.save(company);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public java.util.Optional<Company> find(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Company> findAll() {
		return dao.findAll();
	}

}
