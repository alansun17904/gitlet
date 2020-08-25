package main;

import java.util.Date;
import java.util.Calendar;
import java.util.Arrays;


public class Commit {
	private Blob[] blobs;
	private String author;
	private String hash;
	private String branchName;
	private String message;
	private Calendar date;
	private Commit previousCommit;
	
	/*
	 * Creates the first commit that has a date of 01/01/1970.
	 */
	public Commit(Blob[] blobs) {
		this.blobs = blobs;
		this.author = System.getProperty("user.name");
		this.message = "initial commit";
		this.branchName = "master";
		Calendar c = Calendar.getInstance();
		c.set(1970, 0, 1, 1, 1, 1);
		this.date = c;
		this.hash = this.generateCommitHash();
	}
	
	public Commit(Blob[] blobs, String message, String branchName, 
			Commit previous) {
		this.blobs = blobs;
		this.author = System.getProperty("user.name");
		this.message = message;
		this.branchName = branchName;
		this.date = Calendar.getInstance();
		this.previousCommit = previous;
		this.hash = this.generateCommitHash();
	}
	
	public String getAuthor() {return this.author;}
	
	public String getMessage() {return this.message;}
	
	public Calendar getDate() {return this.date;}
	
	public String getBranch() {return this.branchName;}
	
	public Commit getPreviousCommit() {return this.previousCommit;}
	
	public Blob[] getStagingGround() {return this.blobs;}
	
	public String getHash() {return this.hash;}

	/*
	 * Generates a SHA-1 hash for this commit object.
	 * This is done by hashing all of the hashes of the
	 * Blob objects. So that commits with the same blobs
	 * have the same hash.
	 */
	private String generateCommitHash() {
		if (this.blobs.length == 0) {
			return Utility.hash(" ");
		} else {
			// first sort all file hashes alphabetically
			String[] blobHashes = new String[this.blobs.length];
			for (int i = 0; i < this.blobs.length; i++) { 
				blobHashes[i] = this.blobs[i].getHash();
			}
			Arrays.sort(blobHashes);
			String combinedFileHashes = "";
			for (String blobHash: blobHashes) {
				combinedFileHashes = combinedFileHashes + blobHash;
			}
			return Utility.hash(combinedFileHashes);
		}
	}
	
	public boolean equals(Commit other) {
		return this.getHash().equals(other.getHash());
	}
	
	public Commit add(String filename) {
		Blob additionalFile = new Blob(filename);
		Blob[] newFiles = new Blob[this.blobs.length + 1];
		for (int i=0; i<newFiles.length-1; i++) {
			newFiles[i] = this.blobs[i];
		}
		
		newFiles[newFiles.length-1] = additionalFile;
		
		return new Commit(newFiles, this.getMessage(), this.getBranch(),
				this.getPreviousCommit());
		
	}
	
}
