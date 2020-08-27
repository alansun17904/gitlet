package main;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Commit implements Serializable {
	private List<Blob> blobs;
	private String author;
	private String hash;
	private String branchName;
	private String message;
	private Calendar date;
	private Commit previousCommit;
	
	/*
	 * Creates the first commit that has a date of 01/01/1970.
	 */
	public Commit(List<Blob> blobs) {
		this.blobs = blobs;
		this.author = System.getProperty("user.name");
		this.message = "initial commit";
		this.branchName = "master";
		Calendar c = Calendar.getInstance();
		c.set(1970, 0, 1, 0, 0, 0);
		this.date = c;
		this.hash = this.generateCommitHash();
	}
	
	public Commit(List<Blob> blobs, String message, String branchName, 
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
	
	public List<Blob> getStagingGround() {return this.blobs;}
	
	public String getHash() {return this.hash;}

	/*
	 * Generates a SHA-1 hash for this commit object.
	 * This is done by hashing all of the hashes of the
	 * Blob objects. So that commits with the same blobs
	 * have the same hash.
	 */
	private String generateCommitHash() {
		if (this.blobs.size() == 0) {
			return Utility.hash(" ");
		} else {
			// first sort all file hashes alphabetically
			String[] blobHashes = new String[this.blobs.size()];
			for (int i = 0; i < this.blobs.size(); i++) { 
				blobHashes[i] = this.blobs.get(i).getHash();
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
		// TODO: add case where the filename is already a part of the staging ground.
		// Loop through the list if the filename equates to one in the 
		// blob list then replace it with a new filename 
		// otherwise add it at the very end of the list.
		List<Blob> newFiles = new ArrayList<Blob>(this.blobs.size());
		Blob additionalFile = new Blob(filename);
		for (int i = 0; i < newFiles.size(); i++) {
			String currentFilename = newFiles.get(i).getFilename();
			if (additionalFile.getFilename().equals(currentFilename)) {
				newFiles.set(i, additionalFile);
				return new Commit(newFiles, this.getMessage(), this.getBranch(),
						this.getPreviousCommit());
			} else {
				newFiles.set(i, this.blobs.get(i));
			}
		}
		newFiles.add(additionalFile);
		return new Commit(newFiles, this.getMessage(), this.getBranch(),
						this.getPreviousCommit());
	}
	
	/*
	 * Removes a file from the staging ground and the directory.
	 * If this file does not exist in the staging ground, do nothing
	 * and print out "No reason to remove file."
	 * After removing returns a new commit object that represents 
	 * these changes. 
	 */
	public Commit remove(String filename) {
		boolean fileExists = false;
		for (Blob file: this.blobs) {
			if (file.getFilename().equals(System.getProperty("user.dir") + filename)) {
				fileExists = true;
			}
		}
		if (fileExists) {
			Utility.deleteDirectory(new File(System.getProperty("user.dir") + filename));
			if (this.blobs.size() - 1 == 0) {
				return new Commit(new ArrayList<Blob>() {}, this.getMessage(), 
						this.getBranch(), this.getPreviousCommit());
			}
			List<Blob> newFiles = new ArrayList<Blob>(this.blobs.size() - 1);
			for (int i = 0; i < this.blobs.size(); i++) {
				if (this.blobs.get(i).getFilename() != filename) {
					newFiles.set(i, this.blobs.get(i));
				}
			}
			return new Commit(newFiles, this.getMessage(), 
								this.getBranch(), this.getPreviousCommit());
		} else {
			System.out.println("No reason to remove file");
			return this;
		}
	}
	
	public boolean saveCommit() {
		return false;
	}
}
