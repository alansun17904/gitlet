package tests;

import java.io.File;

public class TestUtility {
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
