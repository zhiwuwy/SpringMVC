package com.whyme.response;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/response")
public class HandleResponseController {
	
	//返回void类型,可以把Controller方法当做Servlet来使用
	@RequestMapping("/test1")
	public void test1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//请求转发:
		request.getRequestDispatcher("").forward(request, response);
		//URL重定向
		response.sendRedirect("");
		//设置共享数据
		request.setAttribute("", "");
		//输出JSON格式
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().println("");
	}
	
	//返回void,文件下载
	@RequestMapping("/test2")
	public void test2(OutputStream out) throws Exception {
		Files.copy(Paths.get(""), out);
	}
	
	//返回ModelAndView
	@RequestMapping("/test3")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
		/*<!-- 配置视图解析器 -->
		<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix" value="/WEB-INF/views/"/>
			<property name="suffix" value=".jsp"/>
		</bean>	*/
		mv.setViewName("welcome");
		mv.addObject("msg","hello!");//设置key和value
		mv.addObject("王阳!!!");//只设置了value,此时会把value类型的首字母作为key,比如这里就是string,小写的
		return mv;
	}
	
	//返回String类型:也就是逻辑视图名称,这里要有模型,跟test3一个效果
	@RequestMapping("/test4")
	public String test4(Model model) {
		model.addAttribute("msg", "hello,SpringMVC!");
		model.addAttribute("王阳");
		return "welcome";
	}
	
	//请求转发,浏览器地址栏不改变,可以共享请求中的数据,不会加前缀后缀,只需要在字符串加一个forward
	@RequestMapping("test5")
	public String test5(Model model) {
		return "forward:/hello.html";
	}
	
	//URL重定向,浏览器地址栏会改变,不能共享请求中的数据
	//原理,response.sendRedirect("");
	@RequestMapping("test6")
	public String test6(Model model) {
		return "redirect:/hello.html";
	}
	/*
	 	redirect:/hello.html,是绝对路径,会从根路径下去找
	 	redirect:hello.html,表示相对路径,会从上一级上下文路径中去寻找资源localhost:/response/welcome.html
	 */
	
	
	//通过flash属性,使URL重定向也能共享数据
	@RequestMapping("/a")
	public String a(RedirectAttributes ra) {
		ra.addAttribute("msg1", "msg1");//参数会出现在地址栏
		ra.addFlashAttribute("msg2", "mag2");//使用flash看不到共享的数据,很安全
		return "redirect:/response/b";
	}
	//通过flash属性,使URL重定向也能共享数据
	@RequestMapping("/b")
	public ModelAndView b(String msg1, @ModelAttribute("msg2")String msg2) {
		System.out.println("msg1:" + msg1);
		System.out.println("msg2:" + msg2);
		return null;
	}
}
