package main;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Utility {
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
}
