package main;


import java.io.File;
import java.io.Serializable;
import java.io.ObjectInput;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Repository {
	private List<Commit> commits;
	private	Commit currentCommit;
	
	public Repository() {
		File newDirectory = new File(System.getProperty("user.dir") + ".gitlet");
		if (newDirectory.exists()) {
			System.out.println("A Gitlet version-control system already"
					+ " exists in the current directory.");
		} else {
			if (!newDirectory.mkdir()) {
				System.out.println("Could not create new repository.");
			}
		}
		Commit firstCommit = new Commit(new ArrayList<Blob>());
		this.currentCommit = firstCommit;
		List<Commit> commits = new ArrayList<Commit>(Arrays.asList(firstCommit));
		System.out.println(commits.size());
	}
	
	public List<Commit> getCommits() {
		return this.commits;
	}
	
	public Commit getCurrentCommit() {
		return this.currentCommit;
	}
	
	public List<Commit> find(String searchString) {
		return new ArrayList<Commit>();
	}
	
	
}
