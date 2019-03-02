package com.NBdiMAN;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.NBdiMAN.domain.Employee;
import com.NBdiMAN.service.IEmployeeService;

@SpringJUnitConfig(locations="classpath:applicationContext.xml")
public class App {
	
	@Autowired
	private IEmployeeService service;
	
	@Test
	void testSave() throws Exception {
		Employee emp = new Employee();
		emp.setUsername("阿柴");
		emp.setAge(6);
		service.save(emp);
	}
	
	@Test
	void testUpdate() throws Exception {
		Employee emp = new Employee();
		emp.setId(7L);
		emp.setUsername("阿柴");
		emp.setAge(6);
		service.update(emp);
	}
	
	@Test
	void testDelete() throws Exception {
		service.delete(7L);
	}
	
	@Test
	void testGet() throws Exception {
		Employee emp = service.get(1L);
		System.out.println(emp);
	}
	
	@Test
	void testListAll() throws Exception {
		List<Employee> emps = service.listAll();
		for (Employee emp : emps) {
			System.out.println(emp);
		}
	}
}
