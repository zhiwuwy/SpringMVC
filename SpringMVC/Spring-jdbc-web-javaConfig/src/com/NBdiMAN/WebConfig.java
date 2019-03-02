package com.NBdiMAN;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.NBdiMAN.web.interceptor.CheckLoginInterceptor;

@Configuration
@EnableWebMvc //启用SpringMVC
@ComponentScan
public class WebConfig implements WebMvcConfigurer{
	
	@Bean
	public CheckLoginInterceptor checkLoginInterceptor() {
		return new CheckLoginInterceptor();
	}
	
	//配置拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(checkLoginInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/login");
	}
	
	//配置JSP视图解析器
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/views/");
		vr.setSuffix(".jsp");
		return vr;
	}
	
	//配置静态资源处理
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
