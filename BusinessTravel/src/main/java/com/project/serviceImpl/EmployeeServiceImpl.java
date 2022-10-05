package com.project.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.EmployeeDao;
import com.project.entities.Employee;
import com.project.services.EmployeeService;
@Service 
@Transactional
public class EmployeeServiceImpl  implements EmployeeService{
	@Autowired 
	private EmployeeDao dao;

	@Override
	public void add(Employee empl) {
		dao.save(empl);
	}

	@Override
	public void update(Employee empl) {
		dao.save(empl);
	}

	@Override
	public java.util.Optional<Employee> find(Long id) {
		return dao.findById(id);
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);
	}

	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}



}
