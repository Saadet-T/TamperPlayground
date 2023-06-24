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
public class LabFour {
	private String isim;
	private String email;
	private String parola;
	private String sehir;
	private String ilce;

	@RequestMapping("/lab4")
	public String lab2(HttpServletRequest request, Model model) {// İstek yapıyor istek bodysinde çalışanların 															// listesi var.
		String username = request.getParameter("username");
		
		if(username.contains(" ")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
		}
		
		try {
			Class.forName("org.postgresql.Driver");

			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/playgroundDB", "postgres",
					"admin");
			Statement stmt = con.createStatement();
			boolean hasMoreResults = stmt.execute(
					"SELECT  US.email AS \"mail\", US.password AS \"parola\", US.username AS \"Username\", US.city AS \"sehir\", US.district AS \"ilce\" FROM accounts US where username='"+ username + "';");
//		while (hasMoreResults) {
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				email = rs.getString("mail");
				isim = rs.getString("Username");
				parola = rs.getString("parola");
				sehir=rs.getString("sehir");
				ilce = rs.getString("ilce");
				System.out.println(isim);
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
		model.addAttribute("lab", "lab4");
		return "getInfo";
	}
}
