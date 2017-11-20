package com.dbms.basiccheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Entry {

	static ConnectionGetter cg = null;
	static Connection con = null;
	static Scanner sc = null;
	static String username = null;
	static String role = null;

 
	private static void loginPage() {
  		// TODO Auto-generated method stub
  		
  		System.out.println("UserName: ");
 		username = sc.next();
  		System.out.println("Password: ");
  		String password = sc.next();
  		
  		//Statement stmt = null;
  		PreparedStatement stmt = null;
  		ResultSet rs = null;
 		role = "";
 }

	
	
	private static void homePage() {
  		// TODO Auto-generated method stub
  	    PreparedStatement stmt = null;
  		ResultSet rs = null;
 		List<String> options = new ArrayList<String>();
 		
  		try{
    		 stmt=con.prepareStatement("SELECT col_name FROM MENU_OPTIONS where role = ? and menu_name = ? order by display_order");
 			 stmt.setString(1, role);
 		     stmt.setString(2, "Home");
  
  		 rs = stmt.executeQuery();
 		 
 		 while(rs.next()){
 			 options.add(rs.getString("col_name"));
 		 }
 		 cg.closeStatement(stmt);
 		 cg.closeResult(rs);
 		for(int i=1; i <= options.size();i++){
 			System.out.println(i + ": " + options.get(i-1));
 		}
 		
 		int optionNo = sc.nextInt();
 		String optionSelected = options.get(optionNo-1);
 		if(optionSelected.equals("Logout") || optionSelected.equals("Log out")){
 		//	startPage();
 		}
 		else if(optionSelected.equals("View Profile") || optionSelected.equals("View/Edit Profile")){
 			//System.out.println("View Profile");
 			viewProfile();
 		}
 		else if(optionSelected.equals("View/Add Courses")){
 			//System.out.println("View/Add Courses");
 			viewAddCourses();
 		}
 		else if(optionSelected.equals("Enroll/Drop A Student")){
 			System.out.println("Enroll/Drop A Student");	
 		}
 		else if (optionSelected.equals("Search/Add questions to Question Bank")){
 			System.out.println("Search/Add questions to Question Bank");
 		}
         
  		}
  		catch(Exception e){
 		e.printStackTrace();
  		}
  		
  	}

	private static void viewProfile() {
 		// TODO Auto-generated method stub
 		PreparedStatement stmt = null;
 		ResultSet rs = null;
 		System.out.println("Press 0 to go back");
 		String table  = "";
 		String id = "";
 		String name = "";
 		if(role.equals("P")){
 			table = "PROFESSOR";
 			id = "PROFESSOR_ID";
 		}
 		else{
 			table = "STUDENT";
 			id = "STUDENT_ID";
 		}
 		try{
 			stmt=con.prepareStatement("SELECT name FROM " + table + " where " + id + " = ?");
 			stmt.setString(1, username);
 			 
 			 rs = stmt.executeQuery();
 			 while(rs.next()){
 				 name = rs.getString("name"); 
 			 }
 		    
 		     }
 		catch(Exception e){
 		   e.printStackTrace();	
 		}
 		cg.closeStatement(stmt);
 		cg.closeResult(rs);
 		String [] nameBreak = name.split("\\s");
 		String fname = nameBreak[0];
 		String lname = "";
 		if(nameBreak.length > 1){
 			lname = nameBreak[1];
 		}
 		System.out.println("First Name: " + fname);
 		System.out.println("Last Name: "  + lname);
 		String lastOption = "";
 		try{
 			stmt=con.prepareStatement("SELECT col_name FROM MENU_OPTIONS where role = ? and menu_name = ? order by display_order");
 			 stmt.setString(1, role);
 		     stmt.setString(2, "Profile");
 		     
 		    rs = stmt.executeQuery();
 		    while(rs.next()){
 		    	lastOption = rs.getString("col_name");
 		    }
 		}
 		catch(Exception e){
 			e.printStackTrace();
 		}
 		cg.closeStatement(stmt);
 		cg.closeResult(rs);
 		System.out.println(lastOption +  ": " + username);
 		int isZero = 1;
 		while(isZero != 0){
 			isZero = sc.nextInt();
 			}
 		homePage();
 		
 	}

	private static void viewAddCourses() {
		// TODO Auto-generated method stub

	}
}