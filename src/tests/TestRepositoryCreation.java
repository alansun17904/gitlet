package tests;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import static org.junit.Assert.*;


public class TestRepositoryCreation {
	public main.Repository r;
	public PrintStream orgStream = System.out;
	public PrintStream fileStream;
		
	
	@Before 
	public void before() {
		// create out.txt to capture all stdout
		// https://www.w3schools.com/java/java_files_create.asp
		try {
			File output = new File("out.txt");
		    if (output.createNewFile()) {
		    	System.out.println("File created: " + output.getName());
		    	
		    } else {
		    	System.out.println("File already exists.");
		    }
	    	this.fileStream = new PrintStream(new FileOutputStream("out.txt",true));
	    	System.setOut(this.fileStream);
	    	
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		this.r = new main.Repository();
	}
	
	@Test
	public void testNewGitInitNewCommit() {
		assertEquals(1, this.r.getCommits().length);
		assertEquals("initial commit", r.getCommits()[0].getMessage());
		assertEquals("master", r.getCommits()[0].getBranch());
		
		Calendar c = Calendar.getInstance(); 
		c.set(1970, 0, 1, 0, 0, 0);
		Date epoch = c.getTime();
		assertTrue(epoch.equals(r.getCommits()[0].getDate()));
	}
	
	@Test
	public void testNewGitInitDir() {
		File dir = new File(".");
		File[] filesList = dir.listFiles();
		int count = 0;
		for (File file: filesList) {
			if (file.getName() == ".gitlet") {
				count++;
				break;
			}
		}
		assertEquals(1, count);
	}

	/*
	 * Check if repo has printed "A Gitlet version-control system 
	 * already exists in the current directory."
	 */
	@Test
	public void testDoubleGitInitDir() {
        // Create duplicate repository
		main.Repository rDup = new main.Repository();
		
		// Read stdout from out.txt file
		File stdout = new File("out.txt");
		try {
			Scanner reader = new Scanner(stdout);
			assertTrue(reader.hasNextLine());
			assertTrue(reader.nextLine().equals("A Gitlet version-control system " + 
										"already exists in the current directory.\n"));
			reader.close();
		} catch (FileNotFoundException e) {
			fail("Program did not generate stdout text for creating "
					+ "a duplicate repository");
		}
		
	}
	
	@Test
	public void testFindInit() {
		assertEquals(1, this.r.find("initial commit").length);
	}
	
	@After 
	public void after() {
		tests.TestUtility.deleteDirectory(new File("out.txt"));
		tests.TestUtility.deleteDirectory(new File(".gitlet"));
	}
}
