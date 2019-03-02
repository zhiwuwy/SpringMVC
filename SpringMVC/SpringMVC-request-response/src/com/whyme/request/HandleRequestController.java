package com.whyme.request;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whyme.vo.FormBean;
import com.whyme.vo.User;

@Controller
@RequestMapping("/request")
public class HandleRequestController {
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/test1")
	public  void test1(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println(request.getParameter("username"));
		System.out.println(request);
		System.out.println(response);
		System.out.println(session);
		System.out.println(this.servletContext);
	}
	//获取请求参数,保证请求参数名称和Controller方法形参同名
	//如果请求参数名和形参名不同,可以用@RequestParam("请求参数名")注解来接收
	@RequestMapping("/test2")
	public void test2(@RequestParam(value = "name", required = true)String username) {
		System.out.println(username);
	}
	
	
	//RESTfull风格传参
	//   /delete?id=110
	//   /delete/110
	//  @PathVariable("")可以将URL中占位符(中括号里面的)绑定到方法的形参中
	@RequestMapping("/delete/{id}")
	public void test3(@PathVariable("id")Long id) {
		System.out.println(id);
	}
	
	//接收一个参数有多个值的情况
	//批量删除? 
	//1.使用数组 ,可以直接接收,public void batchDelete(Long[] ids)  /batchDelete?ids=10&ids=20&ids=30
	//2.使用List,不能直接接收,可以在对象中存在一个集合 /batchDelete?ids[0]=10&ids[1]=20&ids[2]=30
	@RequestMapping("/batchDelete")
	public void batchDelete(FormBean fb) {
		System.out.println(fb.getIds());
		
	}
	
	//把数据直接封装到javaBean对象中
	@RequestMapping("/test4")
	public void test4(User u) {
 		System.out.println(u);
	}
	
	//======================================================
	/*
	 	ModelAttribute注解:
	 	1.给共享的Model数据设置key名,贴在形参上,也可以贴在方法上
	 		针对复合类型:即非简单类型,参数,缺省情况下就会放到model中,共享
	 	2.
	 */
	
	
}
