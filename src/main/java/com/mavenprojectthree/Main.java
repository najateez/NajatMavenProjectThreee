package com.mavenprojectthree;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;




public class Main {
	
	
	public static void insertValuesInDbApiForSectionTable() throws IOException, InterruptedException, Exception {

		// URL->Response->String->Define object-> output -> db
		
		String url = "jdbc:mysql://localhost:3306/apimpthreedb";
		String user = "root";
		String pass = "10@104Ar$";

		HttpClient hClient = HttpClient.newHttpClient();
		HttpRequest requestData = HttpRequest.newBuilder().uri(URI.create("https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=xfjwbyKIMp0lGS0Tn3DSFx3JvLo2iCBX"))
				.build();

		HttpResponse<String> response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());

		 Gson gson=new Gson(); //print all data in one line then insert

		//Gson gson = new GsonBuilder().setPrettyPrinting().create(); // print all data line by line in pretty shape as
																	// json shape first then insert.

		JsonParser jParser = new JsonParser(); // import from library
		JsonElement jElement = jParser.parse(response.body()); // previous string (up) that json shape. import library
		String jsonString = gson.toJson(jElement);
		//System.out.println(jsonString);

		Section data = gson.fromJson(jsonString, Section.class); // the class which contains list of class Results. up.

		Connection con = null;
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(driver);
		con = (Connection) DriverManager.getConnection(url, user, pass);

		for (int i=0;i<response.body().length();i++) {
			String published_date = data.getResults()[i].getPublished_date();
			String section = data.getResults()[i].getSection();
			String subsection = data.getResults()[i].getSubsection();
			

			String SqlQuery = "INSERT INTO Section (published_date,section,subsection) VALUES"
					+ " ('" + published_date + "' ,'" + section + "', '" + subsection + "')";

			System.out.println(SqlQuery);

			try {
				Statement st = con.createStatement();

				// Executing query
				int m1 = st.executeUpdate(SqlQuery);
				if (m1 >= 0)
					System.out.println("Section inserted successfully : " + SqlQuery);
				else
					System.out.println("insertion failed");
				// Closing the connections
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
		con.close();
	}
	
	
	
	public static void insertValuesInDbApiForArticleTable() throws IOException, InterruptedException, Exception {

		// URL->Response->String->Define object-> output -> db
		
		String url = "jdbc:mysql://localhost:3306/apimpthreedb";
		String user = "root";
		String pass = "10@104Ar$";

		HttpClient hClient = HttpClient.newHttpClient();
		HttpRequest requestData = HttpRequest.newBuilder()
				.uri(URI.create("https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=xfjwbyKIMp0lGS0Tn3DSFx3JvLo2iCBX"))
				.build();

		HttpResponse<String> response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());

		 Gson gson=new Gson(); //print all data in one line then insert

		//Gson gson = new GsonBuilder().setPrettyPrinting().create(); // print all data line by line in pretty shape as
																	// json shape first then insert.

		JsonParser jParser = new JsonParser(); // import from library
		JsonElement jElement = jParser.parse(response.body()); // previous string (up) that json shape. import library
		String jsonString = gson.toJson(jElement);
		System.out.println(jsonString);

		Results data = gson.fromJson(jsonString, Results.class); // the class which contains list of class Results. up.

		Connection con = null;
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(driver);
		con = (Connection) DriverManager.getConnection(url, user, pass);

		for (int i=0;i<data.getResults().getBooks().length;i++) {
			String title = data.getResults().getBooks()[i].getTitle();
			String description = data.getResults().getBooks()[i].getDescription();
	//		String author = data.getBooks()[i].getAuthor();
			

			String sqlArticle = "INSERT INTO Article (title,description) VALUES"
					+ " ('" + title + "' ,'" + description + "')";

			System.out.println(sqlArticle);
			
	/*		String sqlAuthor = "INSERT INTO Author (author) VALUES"
					+ " ('" + author + "')";

			System.out.println(sqlAuthor); */

			try {
				Statement st = con.createStatement();

				// Executing query
				int m = st.executeUpdate(sqlArticle);
			//	int m1 = st.executeUpdate(sqlAuthor);
				if (m >= 0 ) {
					System.out.println("Article api values inserted successfully : " + sqlArticle);
			//	System.out.println("Author api values inserted successfully : " +sqlAuthor);
				}else {
					System.out.println("insertion failed");
				// Closing the connections
			} 
			}catch (Exception ex) {
				System.err.println(ex);
			}
		}
		con.close();
	}
	
	
	
	public static void insertValuesInDbApiForAuthorTable() throws IOException, InterruptedException, Exception {

		// URL->Response->String->Define object-> output -> db
		
		String url = "jdbc:mysql://localhost:3306/apimpthreedb";
		String user = "root";
		String pass = "10@104Ar$";

		HttpClient hClient = HttpClient.newHttpClient();
		HttpRequest requestData = HttpRequest.newBuilder()
				.uri(URI.create("https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=xfjwbyKIMp0lGS0Tn3DSFx3JvLo2iCBX"))
				.build();

		HttpResponse<String> response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());
		//System.out.println(response.body());

		 Gson gson=new Gson(); //print all data in one line then insert

		//Gson gson = new GsonBuilder().setPrettyPrinting().create(); // print all data line by line in pretty shape as
																	// json shape first then insert.

		JsonParser jParser = new JsonParser(); // import from library
		JsonElement jElement = jParser.parse(response.body()); // previous string (up) that json shape. import library
		String jsonString = gson.toJson(jElement);
		System.out.println(jsonString);

		Results data = gson.fromJson(jsonString, Results.class); // the class which contains list of class Results. up.

		Connection con = null;
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(driver);
		con = (Connection) DriverManager.getConnection(url, user, pass);

		for (int i=0;i<data.getResults().getBooks().length;i++) {
			String author = data.getResults().getBooks()[i].getAuthor();
			

			String sqlAuthor = "INSERT INTO Author (author) VALUES"
					+ " ('" + author + "')";

			System.out.println(sqlAuthor);
			
			try {
				Statement st = con.createStatement();

				// Executing query
				int m = st.executeUpdate(sqlAuthor);
				if (m >= 0 ) 
					System.out.println("Author api values inserted successfully : " + sqlAuthor);
				else 
					System.out.println("insertion failed");
			}catch (Exception ex) {
				System.err.println(ex);
			}
		}
		con.close();
	}
	
	
	
	
	public static void main(String[] args) throws IOException, InterruptedException, Exception {
		
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.println(" Json->api->save the data to db ");
			System.out.println("*******************************");
			System.out.println("Menu:");
			System.out.println("0:insert values of json api in db for section table.");
			System.out.println("1:insert values of json api in db for article table.");
			System.out.println("2:insert values of json api in db for author table.");
			System.out.println("3:Exit");
			System.out.println("*******************************");
			System.out.println("Enter a number from menu: ");
			int choice = in.nextInt();

			switch (choice) {
			case 0: {
		     insertValuesInDbApiForSectionTable();
		     break;
			}case 1:{
			  insertValuesInDbApiForArticleTable();
			 break;
			}case 2:{
			  insertValuesInDbApiForAuthorTable();
			   break;
			}case 3: {
			 return;
			}default:{
				System.out.println("it is not an option try again ");
			}
			}
		
	}
	
	}

}
