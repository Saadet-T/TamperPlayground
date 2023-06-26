package com.backend.tamperplayground.resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@Controller
public class LabFive {
	private String isim;
	private String email;
	private String parola;
	private String sehir;
	private String ilce;
	private String clean;
	@RequestMapping("/lab5")
	public String lab5(HttpServletRequest request, Model model) {// İstek yapıyor istek bodysinde çalışanların 															// listesi var.
		String username = request.getParameter("username");
		System.out.println(username);
		if(username.toLowerCase().contains("select"))
		{
	
			clean=username.toLowerCase().replace("select", "");
		}else {
			clean=username;
		}
		
		try {
			Class.forName("org.postgresql.Driver");

			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/playgroundDB", "postgres",
					"admin");
			Statement stmt = con.createStatement();
			boolean hasMoreResults = stmt.execute(
					"SELECT  US.email AS \"mail\", US.password AS \"parola\", US.username AS \"Username\", US.city AS \"sehir\", US.district AS \"ilce\" FROM accounts US where username='"+ clean + "';");

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
		model.addAttribute("isim", isim);
		model.addAttribute("mail", email);
		model.addAttribute("password", parola);
		model.addAttribute("sehir", sehir);
		model.addAttribute("ilce", ilce);
		model.addAttribute("lab", "lab5");
		return "getInfo";
	}


}
