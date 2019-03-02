package com.whyme.request;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whyme.vo.FormBean;
import com.whyme.vo.User;

@Controller
@RequestMapping("/request")
public class HandleModelAttributeController {
	
	/*
	 	ModelAttribute注解:
	 	1.给共享的Model数据设置key名,贴在形参上,也可以贴在方法上
	 		针对复合类型:即非简单类型,参数,缺省情况下就自动把复合类型对象会放到model中,缺省的key就是类型首字母小写
	 		在形参上贴ModelAttribute标签,可以设置一个key名,默认的就失效了
	 	2.可以标注一个非请求处理的方法,被标注的方法,每次在请求处理方法之前都会被优先执行,(用来存放多个请求需要共享的数据)
	 */
	@RequestMapping("/11")
	public String test11(@ModelAttribute("MyUser")User u) {
		System.out.println(u);
		return "welcome"; 
	}
	
	//当Controller方法返回一个对象时,默认的逻辑视图是当前请求URL,并共享当前返回对象
	//一般的返回一个javaBean对象的时候,用于JSON操作,必须依赖JSON库(jackson)
	@RequestMapping("/12")
	@ResponseBody
	public Object test12(User u) {
		System.out.println(u);
		return u; 
	}
	
	//使用ModelAttribute注解的方法,可以用来共享数据,会在Controller方法之前执行
	@ModelAttribute
	public void abc(Model model) {
		System.out.println("abc....");
		
	}
	
}
