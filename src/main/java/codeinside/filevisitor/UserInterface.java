package codeinside.filevisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {

	private String directoryToSearch;
	private String textToFind;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public void getUserInput() throws IOException {
		setDirectoryToSearch();
		setTextToFind();
	}

	private void setDirectoryToSearch() throws IOException {
		System.out.println("Please enter directory to search (e.g. c:/folder)");
		directoryToSearch = reader.readLine();
	}
	
	private void setTextToFind() throws IOException {
		System.out.println("Please enter text you want found");
		textToFind = reader.readLine();
	}

	public String getDirectoryToSearch() {
		return directoryToSearch;
	}

	public String getTextToFind() {
		return textToFind;
	}
}
