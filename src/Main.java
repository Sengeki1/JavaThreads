package Files;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.print("Digite o diretorio em que pretende ler: ");
        Scanner nameDir = new Scanner(System.in);
        String path = nameDir.nextLine();

        // input
        System.out.print("Digita o nome a ser procurado: ");
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();

        // file
        Folder fileDir = new Folder(path);
        File[] folder = fileDir.readFile();

        List<Thread> threads = new ArrayList<>();
        int threadsCount = 0;

        List<String> allNames = new ArrayList<>(); // collection of items which every item is unique
        Set<String> duplicateNames = new HashSet<>();

        boolean nameFound = false;

        if (folder != null) {
            for (File file : folder) {
                Runnable runnable = new MyRunnable(input, file, allNames, duplicateNames, nameFound);
                Thread thread = new Thread(runnable);
                thread.start();
                threads.add(thread);
            }
        } else {
            System.out.println("Directory is empty or does not exist.");
        }

        for (Thread thread : threads) { // check if all threads are done
            try {
                thread.join();
                 threadsCount++;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (threadsCount == threads.size()) {
            if (MyRunnable.isNameFound()) {
            fileDir.createFileTxt("Duplicate.txt", duplicateNames);
            fileDir.createFileTxt("Teachers.txt", allNames);
            } else {
                System.out.println("Teacher doesn't exist in the files");
            }
        }
    }
}