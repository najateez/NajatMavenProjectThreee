package com.mavenprojectthree;

public class Results {
	
//	private String published_date;
	
	//from most popular api/ times newswire api
	
	//results->section ->(in api json).
	
	//all properties for section table
	private String published_date;
	private String section;
	private String subsection;
	
	public String getPublished_date() {
		return published_date;
	}
	public void setPublished_date(String published_date) {
		this.published_date = published_date;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSubsection() {
		return subsection;
	}
	public void setSubsection(String subsection) {
		this.subsection = subsection;
	}
	
	

}
