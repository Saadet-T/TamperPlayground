package com.backend.tamperplayground.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomePageController {

	@RequestMapping("/1")
	public String lab1(HttpServletRequest request, HttpServletResponse response) {
		
		return "lab1";
	}
	@RequestMapping("/2")
	public String lab2(HttpServletRequest request, HttpServletResponse response) {
		
		return "getinfo";
	}
	@RequestMapping("/3")
	public String lab3(HttpServletRequest request, HttpServletResponse response) {
		
		return "getinfo";
	}
	@RequestMapping("/4")
	public String lab4(HttpServletRequest request, HttpServletResponse response) {
		
		return "lab4";
	}
	@RequestMapping("/5")
	public String lab5(HttpServletRequest request, HttpServletResponse response) {
		
		return "lab5";
	}



}
