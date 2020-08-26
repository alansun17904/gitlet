package tests;


import main.*;
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
		
		this.r = new Repository();
	}
	
	/*
	 * Check if repo has printed "A Gitlet version-control system 
	 * already exists in the current directory."
	 */
	@Test
	public void testDoubleGitInitDir() {
        // Create duplicate repository
		Repository rDup = new Repository();
		
		// Read stdout from out.txt file
		File stdout = new File("out.txt");
		try {
			Scanner reader = new Scanner(stdout);
			assertTrue(reader.hasNextLine());
			assertTrue(reader.nextLine().equals("A Gitlet version-control system " + 
										"already exists in the current directory."));
			reader.close();
		} catch (FileNotFoundException e) {
			fail("Program did not generate stdout text for creating "
					+ "a duplicate repository");
		}
		
	}
	
	@Test
	public void testNewGitInitNewCommit() {
		assertEquals(1, this.r.getCommits().size());
		assertEquals("initial commit", r.getCommits().get(0).getMessage());
		assertEquals("master", r.getCommits().get(0).getBranch());
		
		Calendar c = Calendar.getInstance(); 
		c.set(1970, 0, 1, 0, 0, 0);
		Date epoch = c.getTime();
		assertTrue(epoch.equals(r.getCommits().get(0).getDate()));
		
		assertTrue(epoch.equals(r.getCurrentCommit().getDate()));
		assertEquals("initial commit", r.getCurrentCommit().getMessage());
		assertEquals("master", r.getCurrentCommit().getBranch());
	}
	
	@Test
	public void testNewGitInitDir() {
		File gitletInitDir = new File(System.getProperty("user.dir") + ".gitlet");
		assertTrue(gitletInitDir.exists());
	}

	
	@Test
	public void testFindInit() {
		assertEquals(1, this.r.find("initial commit").size());
	}
	
	@After 
	public void after() {
		tests.TestUtility.deleteDirectory(new File("out.txt"));
		tests.TestUtility.deleteDirectory(new File(".gitlet"));
	}
}
