package Files;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Digite o diretorio em que pretende ler: ");
        Scanner nameDir = new Scanner(System.in);
        String path = nameDir.nextLine();

        System.out.print("Digita o nome a ser procurado: ");
        // input
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();

        // file
        Folder fileDir = new Folder(path);
        File[] folder = fileDir.readFile();

        if (folder != null) {
            for (File file : folder) {
                Runnable runnable = new MyRunnable(input, file);
                if (!MyRunnable.stopThreads()) {
                    Thread thread = new Thread(runnable); // create a new thread for each file
                    thread.start();
                } else break;
            }
        } else {
            System.out.println("Directory is empty or does not exist.");
        }
    }
}