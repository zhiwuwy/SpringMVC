package com.whyme.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whyme.vo.User;

//处理JSON
@Controller
@RequestMapping("/json")
public class HandleJsonController {
	
	private String applicationJsonUtf8Value;

	/*
	 	处理JSON的注解:
	 	ResponseBody:处理响应,把对象转换为JSON字符串
	 		贴到方法上:只对当前方法做JSON处理
	 		贴到类上:对当前类中所有的方法做JSON处理
	 		
	 	RestController:就是Controller + ResponseBody 
	 	
	 	RequestBody:处理请求,用于读取HTTP请求的内容,用于读取请求的内容,把JSON格式的请求数据封装成对象
	 	
	 	一般的请求数据的格式:
	 	application/x-www-form-urlencoded:传统的key-value格式.处理起来非常方便,不需要RequestBody都可以,,贴上也可以
	 	application/multipart:文件上传的请求,SpringMVC装饰设计模式,既能处理文件上传,也能处理普通表单数据
	 	application/json:参数是JSON格式,此时必须使用ResponseBody
	 	application/xml;
	 */
	//把单个对象/Map转化为json格式
	@RequestMapping("/test1")
	@ResponseBody
	public User test1() {
		User u = new User();
		u.setUsername("whyme");
		u.setAge(17);
		return u;
	}
	
	//把多个对象转化为json格式
	@RequestMapping("/test2")
	@ResponseBody
	public List<User> test2() {
		List<User> users = new ArrayList<>();
		User u1 = new User();
		u1.setUsername("whyme");
		u1.setAge(17);
		User u2 = new User();
		u2.setUsername("whyme");
		u2.setAge(17);
		users.add(u1);
		users.add(u2);
		return users;
		//return Arrays.asList(u1,u2);
	}
	
	//返回一个字符串的时候,当贴上ResponseBody标签后,就不会把返回的字符串当成逻辑视图名称,而是JSON数据的格式
	//@RequestMapping(value="/test3", produces="application/json;charset=utf-8")   效果同下,推荐使用下面的
	@RequestMapping(value="/test3", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String test3() {
		return "success,你好";
	}
}
