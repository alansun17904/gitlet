package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.Blob;
import main.Commit;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

class TestCommit {
	private Blob blob1;
	private Blob blob2;
	private Blob blob3;
	
	private Blob[] blobs;
	private Commit c1;
	private Commit c2;
	
	@Before 
	public void before() {
		this.blob1 = new Blob("/src/tests/resources/introduction1.txt");
		this.blob2 = new Blob("/src/tests/resources/introduction2.txt");
		this.blob3 = new Blob("/src/tests/resoucres/random.txt");
		
		Blob[] staging = {blob1, blob2, blob3};
		
		this.c1 = new Commit(staging, "this is a test commit");
		this.c2 = new Commit(staging, "this is another commit");
	}
	
	@Test
	public void testCommitMessagaContent() {
		assertEquals("this is a test commit", this.c1.getMessage());
		assertEquals("this is another commit", this.c2.getMessage());
	}
	
	@Test
	public void testCommitMessageContent() {
		Date date;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		assertEquals(year, this.c1.getDate().get(Calendar.YEAR));
		assertEquals(month, this.c1.getDate().get(Calendar.MONTH));
		assertEquals(day, this.c1.getDate().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testStagingGroundContent() {
		assertArrayEquals(this.blobs, this.c1.getStagingGround);
	}
	
	@Test
	public void testCommitHashing() {
		assertNotEquals(this.c1.getHash(), this.c2.getHash());
	}
	
	@Test
	public void testAddingToStagingGround() {
		main.Commit addedCommit = c1.add("/src/tests/resources/another_one.txt");
		assertNotEquals(addedCommit.getHash(), this.c1.getHash());
	}
	
}
