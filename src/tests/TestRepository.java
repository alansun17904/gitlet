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

public class TestRepository {
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
		assertEquals(1, this.r.getCommits.lenghth);
		assertEquals("initial commit", r.getCommits[0].getMessage());
		assertEquals("master", r.getCommits[0].getBranchName());
		
		Calendar c = Calendar.getInstance(); 
		c.set(1970, 1, 1, 0, 0, 0);
		Date epoch = c.getTime();
		assertTrue(epoch.equals(r.getCommits[0].getDate()));
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
	
	@Test
	/*
	 * Check if repo has printed "A Gitlet version-control system 
	 * already exists in the current directory."
	 */
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
	
	public static boolean deleteDirectory(File dir) {
		/*
		 * https://javarevisited.blogspot.com/2015/03/how-to-delete-directory-in-java-with-files.html
		 */
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDirectory(children[i]);
				if (!success) {
					return true;
				}
			}
		}
		return dir.delete();
	}
	
	@After 
	public void after() {
		TestRepository.deleteDirectory(new File("out.txt"));
		TestRepository.deleteDirectory(new File(".gitlet"));
	}
}
