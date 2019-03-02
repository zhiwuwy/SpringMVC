package com.NBdiMAN.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.NBdiMAN.dao.IEmployeeDAO;
import com.NBdiMAN.domain.Employee;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	public void save(Employee emp) {
		this.jdbcTemplate.update("INSERT INTO employee (username, age, password, hiredate) VALUES(?, ?)", emp.getUsername(), emp.getAge()
				,emp.getPassword(), emp.getHiredate());
	}

	public void update(Employee emp) {
		this.jdbcTemplate.update("UPDATE employee SET username=?, age=?, password=?, hiredate=? WHERE id=?", emp.getUsername(), emp.getAge(),
				emp.getPassword(), emp.getHiredate(), emp.getId());
	}

	public void delete(Long id) {
		this.jdbcTemplate.update("DELETE FROM employee WHERE id=?", id);
	}

	public Employee get(Long id) {
		List<Employee> list = this.jdbcTemplate.query("SELECT id,username,age,password,hiredate FROM employee WHERE id=?",
				new Object[] { id }, new RowMapper<Employee>() {
					public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
						Employee emp = new Employee();
						emp.setId(rs.getLong("id"));
						emp.setUsername(rs.getString("username"));
						emp.setAge(rs.getInt("age"));
						emp.setPassword(rs.getString("password"));
						emp.setHiredate(rs.getDate("hiredate"));
						return emp;
					}
				});
		return list.size() == 1 ? list.get(0) : null;
	}

	public List<Employee> listAll() {
		return this.jdbcTemplate.query("SELECT id,username,age,password,hiredate FROM employee", new Object[] {}, (rs, rowNum) -> {
			Employee emp = new Employee();
			emp.setId(rs.getLong("id"));
			emp.setUsername(rs.getString("username"));
			emp.setAge(rs.getInt("age"));
			emp.setPassword(rs.getString("password"));
			emp.setHiredate(rs.getDate("hiredate"));
			return emp;
		});
	}

}
