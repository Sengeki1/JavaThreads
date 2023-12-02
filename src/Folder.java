import java.io.File; // import file
import java.io.FileNotFoundException; // handles errors
import java.util.Objects;
import java.util.Scanner; // read text file;
public class Folder {
    private final String input;
    public Folder(String userInput) {
        this.input = userInput;
    }

    public void readFile() {
        File fileDir = new File("/home/marco/Transferências/docentes");
        File[] filesArray = fileDir.listFiles(); // List of the files directory
        /*for (int i = 0; i < filesArray.length; i++) {
            System.out.println(filesArray[i]);
        }*/
        if (filesArray != null) {
                for (File file : filesArray) {
                    Thread thread = new Thread(() -> { // create a new thread for each file
                        try {
                            Scanner myReader = new Scanner(file);
                            int lineNumber = 1;
                            //System.out.println("Contents of file " + file.getName()+ ":");
                            while(myReader.hasNextLine()) { // while there is another line to read
                                String data = myReader.nextLine();
                                if (Objects.equals(data, this.input)) {
                                    System.out.println("Student exist in the file " + file.getName() + " at the line " + lineNumber + ".");
                                }
                                lineNumber++;
                            }
                            myReader.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("An error occurred while reading " + file.getName());
                        }
                    });
                    thread.start(); // start the thread
                }
            } else {
            System.out.println("Directory is empty or does not exist.");
        }
    }
}

