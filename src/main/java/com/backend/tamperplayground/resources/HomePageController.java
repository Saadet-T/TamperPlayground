package com.backend.tamperplayground.resources;

import java.util.Base64;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomePageController {
	static String a ="";
	
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
	
	@RequestMapping("/6")
	 public String lab6(HttpServletRequest request, HttpServletResponse response , Model model) {
		ControlPass code = new ControlPass();
		a=code.passSend();
		String plainString="SaadetElif"+"::::"+a;
		String parameter = Base64.getEncoder().encodeToString(plainString.getBytes());
		model.addAttribute("parameter",parameter);
		System.out.println(a);
		return "lab6";
	}



}
