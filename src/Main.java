import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // input
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();

        // file
        Folder file = new Folder(input);
        file.readFile();
    }
}