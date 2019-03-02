package com.NBdiMAN.domain;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Employee {
	private Long id;
	@NotNull(message = "{employee.username.notnull}")
	@Size(max = 10, min = 4, message = "{employee.username.size}")
	private String username;
	@NotNull(message = "年龄不能为空")
	@Min(value=18, message="年龄不得低于18")
	@Max(value=60, message="年龄不得高于60")
	private Integer age;
	@NotNull(message = "密码不能为空")
	@Size(max = 10, min = 4, message = "密码在4-10个字符之间")
	private String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hiredate;
}
