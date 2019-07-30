package codeinside.filevisitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.io.FileNotFoundException;
import java.util.*;

@ThreadSafe
public class TextFinder {

	private final String searchDirectory;
	private final String textToFind;
	private final List<String> fileExtensions;

	@GuardedBy("this")
	private final Queue<String> files = new LinkedList<>();

	@GuardedBy("this")
	private List<String> paths = new ArrayList<>();

	public TextFinder(String searchDirectory, String textToFind, List<String> fileExtensions) {
		this.searchDirectory = searchDirectory;
		this.textToFind = textToFind;
		this.fileExtensions = fileExtensions;
	}

	public void init() {
		Thread fileFinder = new Thread() {
			@Override
			public void run() {
				FileVisitor fileVisitor1 = new FileVisitor();
				List<String> targetFiles = fileVisitor1.filterExtensions(fileVisitor1.getPaths(searchDirectory),
						fileExtensions);
				synchronized (files) {
					for (String t : targetFiles) {
						files.add(t);
					}
				}
			}
		};
		Thread fileFilter = new Thread() {
			@Override
			public void run() {
				FileVisitor fileVisitor2 = new FileVisitor();
				synchronized (files) {
					if (files.size() != 0) {
						try {
							paths = fileVisitor2.filterByText(files, textToFind);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		fileFinder.run();
		fileFilter.run();
	}

	synchronized List<String> getPaths() {
		return this.paths;
	}
}