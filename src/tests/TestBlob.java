package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBlob {
	private main.Blob blob1;
	private main.Blob blob2;
	private main.Blob blob3;
	
	@Before
	public void before() throws Exception {
		this.blob1 = new main.Blob("/src/tests/resources/introduction1.txt");
		this.blob2 = new main.Blob("/src/tests/resources/introduction2.txt");
		this.blob3 = new main.Blob("/src/tests/resources/random.txt");
	}
	
	@Test
	public void testSHA1() {
		assertNotEquals(main.Blob.hash("hello world"), 
				   		main.Blob.hash("my name is alan"));
	}
	
	@Test
	public void testSHA2() {
		assertNotEquals(main.Blob.hash("   sdf.txt"), 
						main.Blob.hash("sdf.txt"));
	}
	
	@Test 
	public void testSHA3() {
		assertEquals("0190e761bba7bf93fac099718ddb33fd9b3bea1f",
						main.Blob.hash("hello world."));
	}
	
	@Test 
	public void testBlobEqual() {
		assertTrue(this.blob1.equals(blob2));
		assertTrue(this.blob2.equals(blob1));
	}
	
	@Test
	public void testBlobNotEqual() {
		assertTrue(!this.blob1.equals(blob3));
		assertTrue(!this.blob2.equals(blob3));
	}
	
	@After 
	public void after() throws Exception {
		
	}
}
