package Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class MyRunnable implements Runnable {
    private final File file;
    private final String input;
    private volatile boolean interruptflag;

    public MyRunnable(String input, File file) {
        this.file = file;
        this.input = input;
    }
    @Override
    public void run() {
        try {
            Scanner myReader = new Scanner(this.file);
            int lineNumber = 1;
            // System.out.println(Thread.currentThread());
            // System.out.println("Contents of file " + file.getName()+ ":");
            while (myReader.hasNextLine()) { // while there is another line to read
                String data = myReader.nextLine();
                if (Objects.equals(data, this.input)) {
                    System.out.println("Teacher exist in the file " + this.file.getName() + " at the line " + lineNumber + ".");
                    //System.out.println(Thread.currentThread().getName());
                }
                lineNumber++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading " + this.file.getName());
        }
    }
}
