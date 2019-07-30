package codeinside.filevisitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FVRunner {

    public static void main(String[] args) throws IOException {
        ArrayList<String> extensions = new ArrayList<>();
        extensions.add("txt");
        
        UserInterface ui = new UserInterface();
        ui.getUserInput();
        
        TextFinder finder = new TextFinder(ui.getDirectoryToSearch(), ui.getTextToFind(), extensions);
        finder.init();
        
        List<String> paths = finder.getPaths();
        paths.stream().forEach(s -> System.out.println(s));
    }
}
