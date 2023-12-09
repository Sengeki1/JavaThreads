package Queue;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Set;


public class Folder {
    private final String path;
    public Folder(String path) {
        this.path = path;
    }
    public File[] readFile() {
        File fileDir = new File(this.path);
        return fileDir.listFiles();
    }
    public void createFileTxt(String fileName, Set<String> data) {
        try {
            File file = new File(this.path, fileName);
            FileWriter writer = new FileWriter(file);

            for (String name : data) {
                writer.write(name + "\n");
            }

            writer.close();
            System.out.println("File created successfully: " + file.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void createFileTxt(String fileName, List<String> data) {
        try {
            File file = new File(this.path, fileName);
            FileWriter writer = new FileWriter(file);

            for (String name : data) {
                writer.write(name + "\n");
            }

            writer.close();
            System.out.println("File created successfully: " + file.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

