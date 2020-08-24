package main;

import java.util.Date;
import java.util.Calendar;


public class Commit {
	private String author;
	private String branchName;
	private String message;
	private Calendar date;
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Calendar getDate() {
		return this.date;
	}
	
	public String getBranch() {
		return this.branchName;
	}
	
}
