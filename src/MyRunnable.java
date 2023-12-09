package Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class MyRunnable implements Runnable {
    private final File file;
    private final String input;
    private final List<String> uniqueName;
    private final Set<String> duplicateName;
    private static boolean nameFound;

    public MyRunnable(String input, File file, List<String> allName, Set<String> duplicateNames, boolean nameFound) {
        this.file = file;
        this.input = input;
        this.uniqueName = allName;
        this.duplicateName = duplicateNames;
        this.nameFound = nameFound;
    }
    public void setIsNameFound(boolean value) {this.nameFound = value;}
    @Override
    public void run() {
        try {
            Scanner myReader = new Scanner(this.file);
            int lineNumber = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!Objects.equals(data, this.input)) {
                    synchronized (uniqueName) {
                        //System.out.println("Thread " + Thread.currentThread().getName() + " adding name: " + data);
                        this.uniqueName.add(data);
                    }
                } else {
                    System.out.println("Teacher was found in the file " + file.getName() + " at the line " + lineNumber);
                    synchronized (this.duplicateName) { // only 1 thread can execute this block of code at a time
                        //System.out.println("Thread " + Thread.currentThread().getName() + " found duplicate: " + this.input);
                        if (!duplicateName.contains(this.input)) { // if the collection doesn't have the duplicated name
                            duplicateName.add(data); // add the duplicate to the new Array
                            setIsNameFound(true);
                        }
                    }
                }
                lineNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading " + this.file.getName());
        }
    }
    public static boolean isNameFound() {return nameFound;}
}
