 package com.backend.tamperplayground.resources;

import java.util.Base64;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public class ControlPass {
	 private static final Random R = new Random();
	public String passSend() {
		int random = R.nextInt(10);
		StringBuilder builder = new StringBuilder();
	      for (int i = 0; i < 4 ; i++) {
	    	  random = (random*2+3+random^2)%10;
	        builder.append(random);
		  }
	      String code = builder.toString();  
		return code;
	}
	

}
