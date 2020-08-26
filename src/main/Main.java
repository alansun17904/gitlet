package main;


import java.io.File;


public class Main {
	public static void main(String[] args) {
		File f = new File(System.getProperty("user.dir") + "/to_be_removed.txt");
		System.out.println(f.exists());
	}
}
