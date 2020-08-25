package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.Blob;
import main.Commit;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class TestCommit {
	private Blob blob1;
	private Blob blob2;
	private Blob blob3;
	
	private Commit c1;
	private Commit c2;
	
	@Before 
	public void before() {
		this.blob1 = new Blob("/src/tests/resources/introduction1.txt");
		this.blob2 = new Blob("/src/tests/resources/introduction2.txt");
		this.blob3 = new Blob("/src/tests/resources/random.txt");
		
		Blob[] staging1 = {blob1};
		Blob[] staging2 = {blob2, blob3};
		
		System.out.println(blob1.getHash());
		System.out.println(blob2.getHash());
		System.out.println(blob3.getHash());
		
		this.c1 = new Commit(staging1);
		this.c2 = new Commit(staging2, "this is another commit", "master",
							 this.c1);
	}
	
	@Test
	public void testCommitMessageContent() {
		assertEquals("initial commit", this.c1.getMessage());
		assertEquals("this is another commit", this.c2.getMessage());
	}
	
	@Test
	public void testCommitDateTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 0, 1, 0, 0, 0);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		assertEquals(year, this.c1.getDate().get(Calendar.YEAR));
		assertEquals(month, this.c1.getDate().get(Calendar.MONTH));
		assertEquals(day, this.c1.getDate().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testPreviousCommits() {
		assertEquals(this.c1, this.c2.getPreviousCommit());
		assertEquals(null, this.c1.getPreviousCommit());
	}
	
	@Test
	public void testCommitMessageAuthor() {
		String username = System.getProperty("user.name");
		assertEquals(username, c1.getAuthor());
		assertEquals(username, c2.getAuthor());
	}
	
	@Test
	public void testStagingGroundContent() {
		Blob[] staging1 = {blob1};
		assertArrayEquals(staging1, this.c1.getStagingGround());
	}
	
	
	@Test
	public void testAddingToStagingGround() {
		main.Commit addedCommit = c1.add("/src/tests/resources/another_one.txt");
		assertNotEquals(addedCommit.getHash(), this.c1.getHash());
		assertEquals(null, this.c1.getPreviousCommit());
	}
	
	@Test
	public void testRemovingFromStagingGround() {
		fail("Not implemented!");
	}

	@Test
	public void testCommitEquals() {
		Commit c3 = new Commit(new Blob[] {this.blob1},
								"this commit contains a blob",
								"not-master", this.c2);
		assertFalse(this.c1.equals(this.c2));
		assertTrue(this.c1.equals(c3));
	}
	
}
