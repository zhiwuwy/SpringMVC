package com.whyme.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.whyme.vo.Cat;
import com.whyme.vo.Dog;

//处理多个对象的封装
@Controller
public class MultiObjectParamController {
	
	/*
	 	需要把表单中的数据封装到多个对象中去,如果各个对象都有相同的属性,此时要出问题
	 		不知道该把哪一个参数封装到哪一个对象中去
	 */
	//从数据-->对象,封装规则需要我们来设置
	
	//把以dog.开头的参数封装到dog对象中
	@InitBinder("dog")//自定义数据绑定注册,用于将请求参数转换到对应的对象的属性中去
	public void initBinderDogType(WebDataBinder binder) {
		//设置字段以什么作为前缀
		binder.setFieldDefaultPrefix("dog.");
	}
	
	//把以cat.开头的参数封装到cat对象中
	@InitBinder("cat")
	public void initBinderCatType(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("cat.");
	}
	
	@RequestMapping("/save")
	public ModelAndView save(Cat cat, Dog dog) {
		
		System.out.println(cat);
		System.out.println(dog);
		return null;
	}
}
