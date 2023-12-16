import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int students = 10;

        System.out.println("Prova iniciada");
        System.out.println("Docente distribuindo as provas");

        ExamRoom examRoom = new ExamRoom(students);
        Random rand = new Random();
        Folder file = new Folder();

        Thread teacherThread = new Thread(new TeacherRunnable(examRoom));
        teacherThread.start();

        List<Thread> threadList = new ArrayList<>();
        List<Thread> threadList2 = new ArrayList<>();
        int countThread = 0;
        boolean flag = false;

        for (int i = 0; i < students; i++) {
            Thread studentThread = new Thread(new StudentRunnable(i, examRoom, flag, rand, file));
            studentThread.start();
            threadList.add(studentThread);
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
                countThread++;
                flag = true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (countThread == threadList.size()) {
            System.out.println("Alunos podem iniciar as provas");
        }

        for (int i = 0; i < students; i++) {
            Thread studentThread = new Thread(new StudentRunnable(i, examRoom, flag, rand, file));
            studentThread.start();
            threadList2.add(studentThread);
        }

        for (Thread thread : threadList2) {
            try {
                thread.join();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Todos os alunos já terminaram as suas respectivas provas.");
    }
}