package Files;

import java.io.File; // import file

public class Folder {
    private final String path;
    public Folder(String path) {
        this.path = path;
    }

    public File[] readFile() {
        File fileDir = new File(this.path);
        return fileDir.listFiles(); // List of the files directory
    }
}

