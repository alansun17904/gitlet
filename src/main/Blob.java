package main;
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.io.*;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blob implements Serializable {
	private String filename;
	private String hash;
	private String content;
	
	public Blob(String filename) {
		this.filename = System.getProperty("user.dir") + filename;
		this.content = "";
		try {
			File blob = new File(this.filename);
			Scanner reader = new Scanner(blob);
			while (reader.hasNextLine()) {
				this.content = this.content + reader.nextLine();
				if (reader.hasNextLine()) {
					this.content = this.content + "\n";
				}
			}
			reader.close();
			this.hash = Utility.hash(this.content);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
	
	public static void main(String[] args) {

	}
	
	public String getFilename() {
		return this.filename;
	}
	
	public String getHash() {
		return this.hash;
	}
	
	public boolean equals(Blob other) {
		return this.hash.equals(other.hash);
	}
}
