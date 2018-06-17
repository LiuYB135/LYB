package com.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DemoTest {

	@RequestMapping("test")
	public String test(){
		
		return "MyJsp";
	}
	
}
