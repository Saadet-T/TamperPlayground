package com.backend.tamperplayground.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@Controller
public class LabSix {
	private String isim;
	private String email;
	private String parola;
	private String sehir;
	private String ilce;
	private String clean;
	private static final Random R = new Random();
	@RequestMapping("/lab6")
	public String lab6(HttpServletRequest request, Model model) {
		
		
		
		String userName = null;
		String usernameEncoded = request.getParameter("username");
		System.out.println(usernameEncoded);
		byte[] usernameByte = Base64.getDecoder().decode(usernameEncoded);
		System.out.println(usernameByte);
		String takmaAd = new String(usernameByte);
		System.out.println(takmaAd);
		String breakingPoint="::::";
		int breakingIndex = takmaAd.indexOf(breakingPoint);
		String homepageCode=HomePageController.a;
		if(breakingIndex != -1) {
			String otp = takmaAd.substring(breakingIndex+breakingPoint.length());
			System.out.println(otp);
		    	  if(homepageCode.equals(otp))
		    	  {
		    		 userName = takmaAd.substring(0,breakingIndex); //Bu if'in içine bir türlü sokamadım programı
		    		 System.out.println(takmaAd);
		    	  }
			  }
		System.out.println(takmaAd);
		
		try {
			Class.forName("org.postgresql.Driver");

			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/playgroundDB", "postgres",
					"admin");
			Statement stmt = con.createStatement();
		stmt.execute(
					"SELECT  US.email AS \"mail\", US.password AS \"parola\", US.username AS \"Username\", US.city AS \"sehir\", US.district AS \"ilce\" FROM accounts US where username='"+ userName + "';");

			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				email = rs.getString("mail");
				isim = rs.getString("Username");
				parola = rs.getString("parola");
				sehir=rs.getString("sehir");
				ilce = rs.getString("ilce");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		int random = R.nextInt(10);
		StringBuilder builder = new StringBuilder();
	      for (int i = 0; i < 4 ; i++) {
	    	  random = (random*2+3+random^2)%10;
	        builder.append(random);
		  }
	      String code = builder.toString();
	      String plainString = "SaadetElif"+"::::"+code;
	      String parameter = Base64.getEncoder().encodeToString(plainString.getBytes());
	      //Istek buradan gönderilecek olursa giden verinin kontrolünün yapılması lazım kısmı 
		model.addAttribute("isim", isim);
		model.addAttribute("mail", email);
		model.addAttribute("password", parola);
		model.addAttribute("sehir", sehir);
		model.addAttribute("ilce", ilce);
		model.addAttribute("lab", "lab6");
		model.addAttribute("parameter",parameter);
		return "getInfo";
	}


}
