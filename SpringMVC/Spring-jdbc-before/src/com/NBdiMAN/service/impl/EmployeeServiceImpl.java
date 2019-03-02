package com.NBdiMAN.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NBdiMAN.dao.impl.EmployeeDAOImpl;
import com.NBdiMAN.domain.Employee;
import com.NBdiMAN.service.IEmployeeService;
@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	private EmployeeDAOImpl dao;
	
	public void save(Employee emp) {
		dao.save(emp);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public void update(Employee emp) {
		dao.update(emp);
	}

	public Employee get(Long id) {
		Employee emp = dao.get(id);
		return emp;
	}

	public List<Employee> listAll() {
		return dao.listAll();
	}

}
