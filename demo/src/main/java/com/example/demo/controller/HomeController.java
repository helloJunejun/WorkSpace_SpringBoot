package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	// 요청url : http://localhost:8070/
	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "안녕 스프링부트";
	}
}
