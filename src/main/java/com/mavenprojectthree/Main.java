package com.mavenprojectthree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	//	System.out.println(response.body());

		 Gson gson=new Gson(); //print all data in one line then insert

		//Gson gson = new GsonBuilder().setPrettyPrinting().create(); // print all data line by line in pretty shape as
																	// json shape first then insert.

		JsonParser jParser = new JsonParser(); // import from library
		JsonElement jElement = jParser.parse(response.body()); // previous string (up) that json shape. import library
		String jsonString = gson.toJson(jElement);
		//System.out.println(jsonString);

		Section data = gson.fromJson(jsonString, Section.class); 

		Connection con = null;
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(driver);
		con = (Connection) DriverManager.getConnection(url, user, pass);

		for (int i=0;i<data.getResults().length;i++) {
			String published_date = data.getResults()[i].getPublished_date();
			String section = data.getResults()[i].getSection();
			String subsection = data.getResults()[i].getSubsection();
			

			String SqlQuery = "INSERT INTO Section (published_date,section,subsection) VALUES"
					+ " ('" + published_date + "' ,'" + section + "', '" + subsection + "')";

		//	System.out.println(SqlQuery);

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
		
		Statement st = con.createStatement();

		for (int i=0;i<data.getResults().getBooks().length;i++) {
			String title = data.getResults().getBooks()[i].getTitle();
			String description = data.getResults().getBooks()[i].getDescription();
	//		String author = data.getBooks()[i].getAuthor();
			
			Scanner in=new Scanner(System.in);
			
			System.out.println("enter any section name from section table:");
			String section=in.next();
			
			 String sql1="SELECT section_id FROM Section WHERE section='"+section+"'";
	          ResultSet rs = st.executeQuery(sql1);
	          rs.next();
				int section_id = rs.getInt("section_id");  
			

			String sqlArticle = "INSERT INTO Article (title,description,section_id) VALUES"
					+ " ('" + title + "' ,'" + description + "' ,'" + section_id +"')";


			try {
			//	Statement st = con.createStatement();

				// Executing query
				int m = st.executeUpdate(sqlArticle);

				if (m >= 0 ) {
					System.out.println("Article api values inserted successfully : " + sqlArticle);
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
		 Statement st = con.createStatement();

		for (int i=0;i<data.getResults().getBooks().length;i++) {
			String author = data.getResults().getBooks()[i].getAuthor();
			
            Scanner in=new Scanner(System.in);
			
			System.out.println("enter title from article table:");
			// we used here buffer reader to allow read more than one word in one row of one column.
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String title=br.readLine();
//			String title=in.next();
			
			 String sql1="SELECT article_id FROM Article WHERE title='"+title+"'";
	          ResultSet rs = st.executeQuery(sql1);
	          rs.next();
				int article_id = rs.getInt("article_id");
			
				
			String sqlAuthor = "INSERT INTO Author (author,article_id) VALUES"
					+ " ('" + author + "' ,'" + article_id +"')";

	
			
			try {
			//	Statement st = con.createStatement();

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
	
	
/*	public static void top5SectionsWithTheMostArticles(int top5Sections) throws IOException, InterruptedException {
		
		String url = "jdbc:mysql://localhost:3306/apimpthreedb";
		String user = "root";
		String pass = "10@104Ar$";
		
		HttpClient hClient = HttpClient.newHttpClient();
		HttpRequest requestData = HttpRequest.newBuilder()
				.uri(URI.create("https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=xfjwbyKIMp0lGS0Tn3DSFx3JvLo2iCBX"))
				.build();

		HttpResponse<String> response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());
		
		 Gson gson=new Gson();
		 
		JsonParser jParser = new JsonParser(); // import from library
		JsonElement jElement = jParser.parse(response.body()); // previous string (up) that json shape. import library
		String jsonString = gson.toJson(jElement);
		//System.out.println(jsonString);

		Section data = gson.fromJson(jsonString, Section.class);
		
		int count=0;
		Connection con = null;
		PreparedStatement prst = null;
		
		try {
			 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			 DriverManager.registerDriver(driver);
			 
			 con = (Connection) DriverManager.getConnection(url, user, pass);
			 Statement st = con.createStatement();
			 
			 Scanner in=new Scanner(System.in);
			 
			 String sql = "SELECT * FROM Article";
			 ResultSet rs = st.executeQuery(sql);  //ResultSet class import from library
			 
			while (rs.next() && count < top5Sections) {
				int article_id = rs.getInt("article_id");
				String titlee = rs.getString("title");
				String description = rs.getString("description");
				int section_idd = rs.getInt("section_id");
				
				System.out.println("article id: "+ article_id + ", title:" + titlee + ", description:" + description +", section id:" + section_idd);
				count++;
			 }
			con.close();
		}catch (Exception ex) {
			System.err.println(ex);
		}
	} */
	
	
	public static void howManyArticlesWereWrittenByEachAuthor() {
		
		String url = "jdbc:mysql://localhost:3306/apimpthreedb";
	    String user = "root";
        String pass = "10@104Ar$";
        
        Connection con = null;
        
        try {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			con = (Connection) DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			
			Scanner in = new Scanner(System.in);
			
			System.out.println("enter author from author table:");
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String author=br.readLine();
		//	String author=in.next();
			
			// https://stackoverflow.com/questions/8960638/java-mysql-count-number-of-rows
			
			String sql = "select count(*) from Author inner join Article on Author.article_id=Article.article_id where Author.author='" + author + "'";
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				 
				author=rs.getString("count(*)");
				
				System.out.println("Articles were written by this author: " + author );
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	
/* public static void top10ArticlesWithTheMostViews(int top10Articles) throws IOException, InterruptedException {
		
		String url = "jdbc:mysql://localhost:3306/apimpthreedb";
		String user = "root";
		String pass = "10@104Ar$";
		
		HttpClient hClient = HttpClient.newHttpClient();
		HttpRequest requestData = HttpRequest.newBuilder()
				.uri(URI.create("https://api.nytimes.com/svc/books/v3/lists/current/hardcover-fiction.json?api-key=xfjwbyKIMp0lGS0Tn3DSFx3JvLo2iCBX"))
				.build();

		HttpResponse<String> response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());
		
		 Gson gson=new Gson();
		 
		JsonParser jParser = new JsonParser(); // import from library
		JsonElement jElement = jParser.parse(response.body()); // previous string (up) that json shape. import library
		String jsonString = gson.toJson(jElement);
		//System.out.println(jsonString);

		Section data = gson.fromJson(jsonString, Section.class);
		
		int count=0;
		Connection con = null;
		PreparedStatement prst = null;
		
		try {
			 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			 DriverManager.registerDriver(driver);
			 
			 con = (Connection) DriverManager.getConnection(url, user, pass);
			 Statement st = con.createStatement();
			 
			 Scanner in=new Scanner(System.in);
			 
			 String sql = "SELECT * FROM Author";
			 ResultSet rs = st.executeQuery(sql);  //ResultSet class import from library
			 
			while (rs.next() && count < top10Articles) {
				int author_id = rs.getInt("author_id");
				String author = rs.getString("author");
				String description = rs.getString("description");
				int section_idd = rs.getInt("section_id");
				
				System.out.println("author id: "+ author_id + ", author:" + author + ", description:" + description +", section id:" + section_idd);
				count++;
			 }
			con.close();
		}catch (Exception ex) {
			System.err.println(ex);
		}
	} */



public static void howManyArticlesWerePublishedEachMonthInTheYear2021() {
	
	String url = "jdbc:mysql://localhost:3306/apimpthreedb";
    String user = "root";
    String pass = "10@104Ar$";
    
    Connection con = null;
    
    try {
		Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		DriverManager.registerDriver(driver);
		con = (Connection) DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
		
		Scanner in = new Scanner(System.in);
		
		// https://stackoverflow.com/questions/8960638/java-mysql-count-number-of-rows
		
		String sql = "select count(*) from Article inner join Section on Article.section_id=Section.section_id where Section.published_date LIKE '2021%'";
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			 
			String published_date=rs.getString("count(*)");
			
			System.out.println("Articles were published each month in the year 2021: " + published_date );
		}
	} catch (Exception e) {
		System.err.println(e);
	}
	
}

public static void whichSectionHadTheMostArticlesPublishedOnAParticularDay() {
	
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
			System.out.println("4:What are the top 5 sections with the most articles?");
			System.out.println("5:How many articles were written by each author?");
			System.out.println("6:What are the top 10 articles with the most views?");
			System.out.println("7:How many articles were published each month in the year 2021?");
			System.out.println("8:Which section had the most articles published on a particular day?");
			System.out.println("*******************************");
			System.out.println("Enter a number from menu: ");
			int choice = in.nextInt();

			switch (choice) {
			case 0: {
		     insertValuesInDbApiForSectionTable();
		     System.out.println("*******************************");
		     break;
			}case 1:{
			  insertValuesInDbApiForArticleTable();
			  System.out.println("*******************************");
			 break;
			}case 2:{
			  insertValuesInDbApiForAuthorTable();
			  System.out.println("*******************************");
			   break;
			}case 3: {
			 return;
			}case 4: {
			//	top5SectionsWithTheMostArticles(5);
				System.out.println("*******************************");
				break;
			}case 5: {
				howManyArticlesWereWrittenByEachAuthor();
				System.out.println("*******************************");
				break;
			}case 6: {
			
				break;
			}case 7: {
				howManyArticlesWerePublishedEachMonthInTheYear2021();
				break;
			}case 8: {
				
				break;
			}default:{
				System.out.println("it is not an option try again ");
				System.out.println("*******************************");
			}
			}
		
	}
	
	} 

}
