package com.whyme.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class User {
	private Long id;
	private String username;
	private Integer age;
	//@DateTimeFormat(pattern="yyyy-MM-dd")  从前台往后台传参数
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8") //从后台往前台传参数,将Date转换成JSON
	private Date hireDate;
}
