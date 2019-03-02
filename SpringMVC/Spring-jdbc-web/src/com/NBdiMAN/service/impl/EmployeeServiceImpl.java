package com.NBdiMAN.service.impl;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.NBdiMAN.dao.IEmployeeDAO;
import com.NBdiMAN.dao.impl.EmployeeDAOImpl;
import com.NBdiMAN.domain.Employee;
import com.NBdiMAN.service.IEmployeeService;
import com.NBdiMAN.util.UserContext;
@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	private IEmployeeDAO dao;
	
	public void save(Employee emp) {
		dao.save(emp);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public void update(Employee emp) {
		dao.update(emp);
	}
	
	@Transactional(readOnly=true)
	public Employee get(Long id) {
		Employee emp = dao.get(id);
		return emp;
	}

	@Transactional(readOnly=true)
	public List<Employee> listAll() {
		return dao.listAll();
	}
	
	@Transactional(readOnly=true)
	public void login(String username, String password) {
		Employee current = dao.checkLogin(username, password);
		if(current == null) {
			throw new RuntimeException("账号或密码错误");
		}else {
			UserContext.setCurrentUser(current);
		}
	}

}
