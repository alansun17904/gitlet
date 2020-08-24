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


class TestRepositoryUsage {
	
	public main.Repository r;
	
	@Before 
	public void before() {
		r = new main.Repository();
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@After
	public void after() {
		tests.Utility.deleteDirectory(new File(".gitlet"));
	}

}
