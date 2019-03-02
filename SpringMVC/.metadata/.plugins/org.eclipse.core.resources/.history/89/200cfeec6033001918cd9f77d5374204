package com.NBdiMAN.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.NBdiMAN.domain.Employee;
import com.NBdiMAN.service.IEmployeeService;

@Controller
public class LoginController {
	
	@Autowired
	private IEmployeeService service;
	
	@RequestMapping("/login")
	public String login(String username, String password, HttpSession session) {
		System.out.println("LoginController.login()");
		try {
			service.login(username, password);
		} catch (Exception e) {
			session.setAttribute("errorMsg", e.getMessage());
			return "redirect:/login.jsp";
		}
		return "redirect:/employee/list";
	}
}
