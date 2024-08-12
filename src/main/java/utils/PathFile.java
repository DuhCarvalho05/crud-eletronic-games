package utils;

import java.io.File;

public class PathFile {

	private static PathFile instance = null;
	private final String rootPath;

	private PathFile() {
		File[] rootDrive = File.listRoots();
    	String username = System.getProperty("user.name");
    	String root = rootDrive[0].toString();

    	this.rootPath = String.format("%sUsers/%s/", root, username);
	}

	public static PathFile getInstance() {
		if (instance == null) {
			instance = new PathFile();
		}
		return instance;
	}

	public String getPath() {
		return rootPath;
	}

}
