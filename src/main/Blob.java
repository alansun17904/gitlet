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
	public String filename;
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
			this.hash = Blob.hash(this.content);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
	
	public static void main(String[] args) {

	}
	
	public static String hash(String hashText) {
		/*
		 * https://www.geeksforgeeks.org/sha-1-hash-in-java/
		 */
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] messageDigest = md.digest(hashText.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashedtext = no.toString(16); 
			while (hashedtext.length() < 40) { 
                hashedtext = "0" + hashedtext; 
            }
			System.out.println(hashedtext);
			return hashedtext; 
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	
	public boolean equals(Blob other) {
		return this.hash.equals(other.hash);
	}
}
