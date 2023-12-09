package Files;

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
    private final Set<String> seenNames;

    public MyRunnable(String input, File file, List<String> allName, Set<String> duplicateNames, Set<String> seenNames) {
        this.file = file;
        this.input = input;
        this.uniqueName = allName;
        this.duplicateName = duplicateNames;
        this.seenNames = seenNames;
    }
    @Override
    public void run() {
        try {
            Scanner myReader = new Scanner(this.file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                synchronized (seenNames) { // only 1 thread can execute this block of code at a time
                    if (!seenNames.add(data)) { // If a name is already in the set, it's considered a duplicate
                        synchronized (this.duplicateName) {
                            duplicateName.add(data);
                        }
                    } else {
                        synchronized (uniqueName) {
                            uniqueName.add(data);
                        }
                    }
                }
               /* synchronized (uniqueName) {
                    //System.out.println("Thread " + Thread.currentThread().getName() + " adding name: " + data);
                    this.uniqueName.add(data);
                }
                if (Objects.equals(data, this.input)) {
                    synchronized (this.duplicateName) { // only 1 thread can execute this block of code at a time
                        //System.out.println("Thread " + Thread.currentThread().getName() + " found duplicate: " + this.input);
                        if (!duplicateName.contains(this.input)) { // if the collection doesn't have the duplicated name
                            duplicateName.add(data); // add the duplicate to the new Array
                        }
                    }
                } */
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading " + this.file.getName());
        }
    }
}
