package com.mavenprojectthree;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class Tables {
	
public static void isApiSectionTableCreated() {
		
		String url = "jdbc:mysql://localhost:3306/apimpthreedb";
		 String user = "root";
	     String pass = "10@104Ar$"; 
			
	     // can not create table with duplicate column name
		String apiSectionTable = "CREATE TABLE Section (" 
		        + "section_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,"  
		        + "published_date VARCHAR(100)," 
		        + "section VARCHAR(100),"
		        + "subsection VARCHAR(100))"; 

	        Connection con = null;
	        
	        try {

	            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
	            DriverManager.registerDriver(driver);
	            
	            con = DriverManager.getConnection(url, user,pass);
	            Statement st = con.createStatement();

	            int m = st.executeUpdate(apiSectionTable);
	            if (m <=  1) {
	                System.out.println("table Section created successfully : " + apiSectionTable);
	                
	            }
	            else {
	                System.out.println("table Section not created, try again");
	            }
	            con.close();
	        } catch (Exception ex) {
	            System.err.println(ex);
	  }
	        
	}


public static void isApiAuthorTableCreated() {
	
	String url = "jdbc:mysql://localhost:3306/apimpthreedb";
	 String user = "root";
     String pass = "10@104Ar$"; 
		
     // can not create table with duplicate column name
	String apiAuthorTable = "CREATE TABLE Author (" 
	        + "author_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,"
	        + "author VARCHAR(100),"
	        + "article_id int REFERENCES Article (article_id))"; 
	
        Connection con = null;
        
        try {

            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            
            con = DriverManager.getConnection(url, user,pass);
            Statement st = con.createStatement();

            int m = st.executeUpdate(apiAuthorTable);
            if (m <=  1) {
                System.out.println("table Author created successfully : " + apiAuthorTable);
                
            }
            else {
                System.out.println("table Author not created, try again");
            }
            con.close();
        } catch (Exception ex) {
            System.err.println(ex);
  }
        
}


public static void isApiArticleTableCreated() {
	
	String url = "jdbc:mysql://localhost:3306/apimpthreedb";
	 String user = "root";
     String pass = "10@104Ar$"; 
		
     // can not create table with duplicate column name
	String apiArticleTable = "CREATE TABLE Article (" 
	        + "article_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,"  
	        + "title VARCHAR(100),"  
	        + "description VARCHAR(700),"
	        + "section_id int REFERENCES Section (section_id))"; 
	
        Connection con = null;
        
        try {

            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            
            con = DriverManager.getConnection(url, user,pass);
            Statement st = con.createStatement();

            int m = st.executeUpdate(apiArticleTable);
            if (m <=  1) {
                System.out.println("table Article created successfully : " + apiArticleTable);
                
            }
            else {
                System.out.println("table Article not created, try again");
            }
            con.close();
        } catch (Exception ex) {
            System.err.println(ex);
  }
        
}

	public static void main(String[] args) {
		
		isApiSectionTableCreated();
		isApiAuthorTableCreated();
		isApiArticleTableCreated();
		

	}

}
