class ExamRoom {
    public int students;
    private int examsReady;
    public ExamRoom(int students) {
        this.students = students;
        this.examsReady = 0;
    }
    public synchronized void takeExam(int studentId) {
        try {
            while (examsReady == 0) {
                wait(); // wait for teacher to finish
            }
            System.out.println("Aluno " + studentId + " pegou a prova");
            examsReady--; // decrements by 1 each time a thread comes to this block, so only 2 threads can enter this method
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void examsReady(int i) {
        examsReady += i;
        notifyAll(); // notify to student thread to wake up
    }
    public synchronized void startExam(int studentId, boolean flag) {
        if (flag == true) {
            System.out.println("Aluno " + studentId + " e iniciei a minha Prova");
        }
    }
}
