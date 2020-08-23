package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBlob {
	
	@Before
	public void before() throws Exception {
		main.Blob blob = new main.Blob("../tests/introduction.txt");
	}
	
	@Test
	public void testSHA1() {
		assertNotEquals(main.Blob.hash("hello world"), 
				   		main.Blob.hash("my name is alan"));
		assertNotEquals(main.Blob.hash("   sdf.txt"), 
						main.Blob.hash("sdf.txt"));
		assertEquals("0190E761BBA7BF93FAC099718DDB33FD9B3BEA1F",
						main.Blob.hash("hello world."));
	}
	
	@After 
	public void after() throws Exception {
		
	}
}
