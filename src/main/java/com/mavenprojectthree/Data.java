package com.mavenprojectthree;

public class Data {
	
	/* here can not object chain with results class because it has 2 api's books and mostPopular 
	   properties, so the result will be null for section table when we want to insert values
	   for article and author table. that's why should directly connect with books class.
	 */
	Books[] books;

	public Books[] getBooks() {
		return books;
	}

	public void setBooks(Books[] books) {
		this.books = books;
	}

}
