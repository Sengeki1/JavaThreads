import java.util.Random;

class StudentRunnable implements Runnable {
    private int student;
    private ExamRoom examRoom;
    private boolean flag;
    private Random rand;
    private Folder file;
    private static final int MAX_SLEEP_TIME = 10000;

    public StudentRunnable(int student, ExamRoom examRoom, boolean flag, Random rand, Folder file) {
        this.student = student;
        this.examRoom = examRoom;
        this.flag = flag;
        this.rand = rand;
        this.file = file;
    }
    @Override
    public void run() {
        if (flag) {
            examRoom.startExam(student, flag);
            try {
                int time = rand.nextInt(MAX_SLEEP_TIME);
                Thread.sleep(time);
                System.out.println("Aluno " + student + " terminou!");
                file.createFileTxt(student, Thread.currentThread().getName(), time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            examRoom.takeExam(student);
        }
    }
}
