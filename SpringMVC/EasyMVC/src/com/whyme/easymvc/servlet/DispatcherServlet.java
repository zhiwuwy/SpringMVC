package com.whyme.easymvc.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whyme.easymvc.annotation.Controller;
import com.whyme.easymvc.annotation.RequestMapping;
import com.whyme.easymvc.bean.ControllerBean;
import com.whyme.easymvc.bean.ModelAndView;
import com.whyme.easymvc.util.ClassUtil;

//前端控制器，分发
public class DispatcherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//存储每一个URL对应一个Controller方法
	private Map<String, ControllerBean> urlMap = new HashMap<>();
	
	public void init() throws ServletException {
		//扫描哪些包中使用Controller注解标注的类
		List<Class<?>> classList = ClassUtil.getClassListByAnnotation("com.whyme.hello", Controller.class);
		
		for (Class<?> clazz : classList) {
			Method[] methods = clazz.getDeclaredMethods();
			if(methods != null && methods.length > 0) {
				for (Method  method : methods) {
					//判断Controller类中每一个方法是否被RequestMapping注解标注
					RequestMapping rm  = method.getAnnotation(RequestMapping.class);
					if(rm != null) {
						String url = rm.value();
						urlMap.put(url, new ControllerBean(clazz, method));
					}
				}
			}
		}
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求的资源名称
		String requestURI = request.getRequestURI();
		if("/favicon.ico".equals(requestURI)) {
			return ;
		}
		
		if(requestURI != null && requestURI.contains(".")) {
			requestURI = requestURI.substring(0, requestURI.lastIndexOf("."));
		}
		
		//根据请求的URL,寻找对应的Controller方法的包装对象
		ControllerBean controllerBean = urlMap.get(requestURI);
		if(controllerBean == null) {
			request.getRequestDispatcher(requestURI).forward(request, response);
			return;
		}
		//获取当前请求对应的Controller类和Controller方法
		Class<?> controllerClass = controllerBean.getControllerClass();
		Method controllerMethod = controllerBean.getControllerMethod();
		
		try {
			Object controllerObject = controllerClass.newInstance();
			//调用相应的Controller方法,返回ModelAndView对象
			Object ret = controllerMethod.invoke(controllerObject);
			if(ret != null && ret.getClass() == ModelAndView.class) {
				//处理响应
				this.handleResult(request, response, (ModelAndView) ret);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	private void handleResult(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {
		String viewName = mv.getViewName();	
		Map<String, Object> attrs = mv.getModel();
		for(Map.Entry<String, Object> entry : attrs.entrySet()) {
			request.setAttribute(entry.getKey(), entry.getValue());
		}
		String prefix = "WEB-INF/views/";
		String suffix = ".jsp";
		//物理视图 = 前缀 + 逻辑视图 + 后缀 
		request.getRequestDispatcher(prefix + viewName + suffix).forward(request, response);
	
	}
	
}
