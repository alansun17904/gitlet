package main;


import java.io.File;
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
			return hashedtext; 
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean deleteDirectory(File dir) {
		/*
		 * https://javarevisited.blogspot.com/2015/03/how-to-delete-directory-in-java-with-files.html
		 */
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDirectory(children[i]);
				if (!success) {
					return true;
				}
			}
		}
		return dir.delete();
	}
}
