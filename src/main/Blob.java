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
			System.out.println(this.content);
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
	
	public static void main(String[] args) {
		
	}
	
	public static String hash(String hashText) {
		return "";
	}
	
	public boolean equals(Blob other) {
		return this.hash.equals(other.hash);
	}
}
